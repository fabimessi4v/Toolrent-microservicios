package com.proyectogradle.msmontosytarifas.service;

import com.proyectogradle.msmontosytarifas.enitity.fee;
import com.proyectogradle.msmontosytarifas.repository.feeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class  feeService {
    @Autowired
    private feeRepository feeRepository;

    // Guardar o actualizar tarifa
    public fee saveFee(String type, Float value, String updatedBy) {
        fee fee = feeRepository.findByType(type)
                .orElse(new fee(UUID.randomUUID().toString(), type, value));

        fee.setValue(value);
        fee.setUpdatedBy(updatedBy);
        return feeRepository.save(fee);
    }

    // Obtener tarifa por tipo
    public fee getFee(String type) {
        return feeRepository.findByType(type)
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada: " + type));
    }
}