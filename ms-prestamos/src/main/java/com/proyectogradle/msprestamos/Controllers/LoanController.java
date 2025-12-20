package com.proyectogradle.msprestamos.Controllers;

import com.proyectogradle.msprestamos.Entities.Loans;
import com.proyectogradle.msprestamos.Service.LoansServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoansServiceImpl loansService;

    // RF2.1 Registrar Préstamo
    @PostMapping
    public ResponseEntity<?> createLoan(@RequestBody Map<String, Object> payload) {
        try {
            // Extraemos datos del JSON
            String userId = (String) payload.get("userId");
            String toolId = (String) payload.get("toolId");
            String customerId = (String) payload.get("customerId");

            // Conversión simple de fechas (Asumiendo que envían timestamp o string ISO)
            Date deliveryDate = new Date(); // Asumimos hoy
            // Asumimos que dueDate viene como long (timestamp) o lo calculas
            Date dueDate = new Date((Long) payload.get("dueDate"));

            Loans newLoan = loansService.registerLoan(userId, toolId, customerId, deliveryDate, dueDate);

            return ResponseEntity.ok(newLoan);

        } catch (IllegalStateException e) {
            // Errores de negocio (Validaciones, Stock, Multas) -> 409 Conflict o 400 Bad Request
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            // Errores inesperados -> 500
            return ResponseEntity.internalServerError().body("Error al procesar el préstamo: " + e.getMessage());
        }
    }


    @PostMapping("/return")
    public ResponseEntity<?> returnLoan(@RequestBody Map<String, Object> payload) {
        try {
            // Extraemos los datos del JSON
            String loanId = (String) payload.get("loanId");

            // Convertimos la fecha de devolución (esperamos un Long timestamp)
            Long returnDateMillis = (Long) payload.get("returnDate");
            if (returnDateMillis == null) {
                return ResponseEntity.badRequest().body("Debe proporcionar una fecha de devolución (returnDate)");
            }
            Date returnDate = new Date(returnDateMillis);

            // Llamamos al servicio para procesar la devolución y multa
            Loans updatedLoan = loansService.registerReturn(loanId, returnDate);

            return ResponseEntity.ok(updatedLoan);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la devolución: " + e.getMessage());
        }
    }



















}