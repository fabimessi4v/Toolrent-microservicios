package com.proyectogradle.msprestamos.Repository;

import com.proyectogradle.msprestamos.Entities.Loans;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loans, String> {

    /**
     * Cuenta cuántos préstamos tiene un cliente en estado "ACTIVE".
     * Usado para validar que no supere el límite de 5.
     */
    @Query("SELECT COUNT(l) FROM Loans l WHERE l.customerId = :customerId AND l.status = 'ACTIVE'")
    int countActiveLoansByCustomerId(@Param("customerId") String customerId);

    /**
     * RF2.5: Cuenta cuántos préstamos tiene vencidos el cliente.
     * Un préstamo está vencido si está "ACTIVE" y la fecha de vencimiento (dueDate)
     * es anterior a la fecha actual (CURRENT_DATE).
     */
    @Query("SELECT COUNT(l) FROM Loans l WHERE l.customerId = :customerId AND l.status = 'ACTIVE' AND l.dueDate < CURRENT_DATE")
    int countOverdueLoans(@Param("customerId") String customerId);

    /**
     * Extra: Método para listar el historial de préstamos de un cliente específico.
     * Útil si necesitas mostrar "Mis Préstamos" en el frontend.
     */
    List<Loans> findByCustomerId(String customerId);
}