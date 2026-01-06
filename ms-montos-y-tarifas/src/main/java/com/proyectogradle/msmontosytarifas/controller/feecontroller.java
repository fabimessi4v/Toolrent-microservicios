package com.proyectogradle.msmontosytarifas.controller;

import com.proyectogradle.msmontosytarifas.enitity.fee;
import com.proyectogradle.msmontosytarifas.service.feeService;
import org.springframework.beans.factory.annotation. Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind. annotation.*;

import java.util. List;
import java.util.Map;

@RestController
@RequestMapping("/api/fee")
public class feecontroller {

    @Autowired
    private feeService feeService;
    // PUT /api/fee/{type} - Guardar o actualizar
    @PutMapping("/{type}")
    public ResponseEntity<fee> updateFee(
            @PathVariable String type,
            @RequestBody Map<String, Object> body) {

        Float value = body.get("value") != null ?
                ((Number) body.get("value")).floatValue() : null;

        if (value == null || value < 0) {
            return ResponseEntity.badRequest().build();
        }

        String updatedBy = (String) body.getOrDefault("updatedBy", "admin");

        fee fee = feeService. saveFee(type, value, updatedBy);
        return ResponseEntity.ok(fee);
    }

    // GET /api/fee/{type} - Obtener tarifa
    @GetMapping("/{type}")
    public ResponseEntity<fee> getFee(@PathVariable String type) {
        try {
            fee fee = feeService.getFee(type);
            return ResponseEntity.ok(fee);
        } catch (RuntimeException e) {
            return ResponseEntity. notFound().build();
        }
    }


}