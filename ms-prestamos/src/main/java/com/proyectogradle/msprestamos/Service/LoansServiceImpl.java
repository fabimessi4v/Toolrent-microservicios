package com.proyectogradle.msprestamos.Service;

import com.proyectogradle.msprestamos.DTO.CustomerDTO;
import com.proyectogradle.msprestamos.Entities.Loans;
import com.proyectogradle.msprestamos.Repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoansServiceImpl {

    private final LoanRepository loanRepository;
    // Inyectamos nuestros "mensajeros" (Integration Services)
    private final InventoryIntegrationService inventoryIntegration;
    private final CustomerIntegrationService customerIntegration;

    // Tarifa fija de multa (RF2.4)
    private static final float DAILY_LATE_FEE = 1500.0f;

    // Constructor manual (Sin Lombok)
    public LoansServiceImpl(LoanRepository loanRepository,
                            InventoryIntegrationService inventoryIntegration,
                            CustomerIntegrationService customerIntegration) {
        this.loanRepository = loanRepository;
        this.inventoryIntegration = inventoryIntegration;
        this.customerIntegration = customerIntegration;
    }

    /**
     * RF2.1: Registrar Préstamo
     * RF2.2: Validar disponibilidad remota
     * RF2.5: Validar bloqueos
     */
    @Transactional
    public Loans registerLoan(String userId, String toolId, String customerId, Date deliveryDate, Date dueDate) {

        // --- PASO 1: Validaciones de Cliente (Híbrido: Remoto y Local) ---

        // A. Obtener datos del MS Clientes (vía RestTemplate)
        CustomerDTO customer = customerIntegration.getCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("El cliente no existe en el sistema.");
        }

        // B. Regla: Multas impagas (Dato que viene del MS Clientes)
        if (customer.getUnpaidFines() > 0) {
            throw new IllegalStateException("El cliente tiene multas impagas y no puede solicitar préstamos.");
        }

        // C. Regla: Límite de 5 préstamos (Dato Local)
        if (loanRepository.countActiveLoansByCustomerId(customerId) >= 5) {
            throw new IllegalStateException("El cliente ya tiene 5 préstamos activos.");
        }

        // D. RF2.5 Regla: Bloqueo por atrasos (Dato Local)
        // Verificamos en NUESTRA base de datos si tiene préstamos vencidos
        if (loanRepository.countOverdueLoans(customerId) > 0) {
            throw new IllegalStateException("El cliente está bloqueado por tener préstamos vencidos.");
        }

        // --- PASO 2: Validar Disponibilidad (RF2.2 - Remoto) ---
        // Preguntamos al MS Inventario si hay stock
        boolean isAvailable = inventoryIntegration.checkAvailability(toolId);
        if (!isAvailable) {
            throw new IllegalStateException("La herramienta no tiene stock disponible.");
        }

        // Validar coherencia de fechas
        if (dueDate.before(deliveryDate)) {
            throw new IllegalArgumentException("La fecha de devolución no puede ser anterior a la de entrega.");
        }

        // --- PASO 3: Guardar el Préstamo (Local) ---
        Loans loan = new Loans();
        loan.setId(UUID.randomUUID().toString());
        loan.setClientId(userId);
        loan.setToolId(toolId);
        loan.setCustomerId(customerId);
        loan.setDeliveryDate(deliveryDate);
        loan.setDueDate(dueDate);
        loan.setStatus("ACTIVE");
        loan.setFine(0f);

        // Guardamos en MySQL local. Si falla algo más abajo, esto se revierte automáticamente (@Transactional).
        Loans savedLoan = loanRepository.save(loan);

        // --- PASO 4: Actualizar Kardex y Stock (Remoto) ---
        try {
            // Enviamos la orden al MS Inventario
            inventoryIntegration.registerMovement(
                    toolId,
                    -1,             // Restar 1
                    "LOAN",         // Tipo Préstamo
                    userId
            );
        } catch (Exception e) {
            // CRÍTICO: Si falla la comunicación con el MS Inventario (está caído o da error),
            // lanzamos RuntimeException para que Spring haga ROLLBACK del préstamo local.
            // Así no quedamos con un préstamo creado sin haber descontado el stock.
            throw new RuntimeException("Error al sincronizar con Inventario. Préstamo cancelado.", e);
        }

        return savedLoan;
    }

    /**
     * RF2.3: Registrar Devolución
     * RF2.4: Calcular Multas
     */
    @Transactional
    public Loans registerReturn(String loanId, Date returnDate) {
        // Buscar préstamo local
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("El préstamo no existe."));

        if (!"ACTIVE".equals(loan.getStatus())) {
            throw new IllegalStateException("El préstamo no está activo (ya fue devuelto).");
        }

        if (returnDate.before(loan.getDeliveryDate())) {
            throw new IllegalArgumentException("Fecha de devolución inválida.");
        }

        // --- PASO 1: Calcular Multa (RF2.4) ---
        float multa = calculateLateFee(loan, returnDate);

        // --- PASO 2: Actualizar Estado Local ---
        loan.setReturnDate(returnDate);
        loan.setFine(multa);
        loan.setStatus("RETURNED");

        loanRepository.save(loan);

        // --- PASO 3: Actualizar Kardex y Stock (Remoto) ---
        try {
            inventoryIntegration.registerMovement(
                    loan.getToolId(),
                    1,              // Sumar 1
                    "RETURN",       // Tipo Devolución// Usuario original (o podrías pasar el actual si lo recibes por parámetro)
                    loan.getId()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al sincronizar devolución con Inventario.", e);
        }

        return loan;
    }

    // Método auxiliar para cálculo de multas
    private float calculateLateFee(Loans loan, Date actualReturnDate) {
        // Si devuelve antes o el mismo día pactado, multa es 0
        if (!actualReturnDate.after(loan.getDueDate())) {
            return 0f;
        }

        // Calcular diferencia en milisegundos
        long diffInMillies = actualReturnDate.getTime() - loan.getDueDate().getTime();
        // Convertir a días
        long diffDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diffDays <= 0) return 0f;

        return diffDays * DAILY_LATE_FEE;
    }
}