package com.proyectogradle.msmontosytarifas.service;

import org.springframework.stereotype.Service;

@Service
public class  feeService {
    private final FeeRepository feeRepository;
    private final RestTemplate restTemplate; // Para consultar al MS-Inventory

    public FeeServiceImpl(feeRepository feeRepository, RestTemplate restTemplate) {
        this.feeRepository = feeRepository;
        this.restTemplate = restTemplate;
    }

    // RF4.2: Configurar tarifa diaria de multa
    // Usamos un registro especial con loanId = 'CONFIG' para guardar este valor global
    public void upsertPenaltyRate(Float newValue) {
        Fee config = feeRepository.findByTypeAndLoanId("LATE_FEE_CONFIG", "SYSTEM")
                .orElse(new Fee());

        if (config.getId() == null) {
            config.setId(UUID.randomUUID().toString());
            config.setType("LATE_FEE_CONFIG");
            config.setLoanId("SYSTEM");
            config.setCustomerId("SYSTEM");
        }
        config.setValue(newValue);
        config.setCreatedAt(Instant.now());
        feeRepository.save(config);
    }

    // Método para calcular y registrar una multa (Gatillado por MS-Loans)
    public void applyLateFee(String customerId, String loanId, int daysLate) {
        // 1. Obtener la tasa de multa desde nuestra propia tabla de config
        Fee config = feeRepository.findByTypeAndLoanId("LATE_FEE_CONFIG", "SYSTEM")
                .orElseThrow(() -> new RuntimeException("Tarifa de multa no configurada"));

        // 2. Crear el registro del cargo para el cliente
        Fee charge = new Fee();
        charge.setId(UUID.randomUUID().toString());
        charge.setCustomerId(customerId);
        charge.setLoanId(loanId);
        charge.setType("LATE_FEE");
        charge.setValue(config.getValue() * daysLate);
        charge.setCreatedAt(Instant.now());

        feeRepository.save(charge);
    }
}