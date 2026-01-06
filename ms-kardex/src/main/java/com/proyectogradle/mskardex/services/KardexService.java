package com.proyectogradle.mskardex.services;

import com.proyectogradle.mskardex.entity. Kardex;
import com. proyectogradle.mskardex.repository.KardexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java. util.List;
import java. util.Optional;
import java.util.UUID;

@Service
@Transactional
public class KardexService {

    @Autowired
    private KardexRepository kardexRepository;

    // Obtener todos los movimientos
    public List<Kardex> getAllKardex() {
        return kardexRepository.findAll();
    }

    // Obtener un movimiento por ID
    public Optional<Kardex> getKardexById(String id) {
        return kardexRepository.findById(id);
    }

    // Crear un nuevo movimiento
    public Kardex createKardex(Kardex kardex) {
        // Generar UUID si no viene
        if (kardex.getId() == null || kardex.getId().isEmpty()) {
            kardex.setId(UUID.randomUUID().toString());
        }
        return kardexRepository.save(kardex);
    }

    // Actualizar un movimiento existente
    public Kardex updateKardex(String id, Kardex kardexDetails) {
        return kardexRepository.findById(id)
                .map(kardex -> {
                    kardex.setToolId(kardexDetails.getToolId());
                    kardex.setUserId(kardexDetails.getUserId());
                    kardex.setLoansId(kardexDetails.getLoansId());
                    kardex.setType(kardexDetails.getType());
                    kardex.setQuantity(kardexDetails.getQuantity());
                    kardex.setMovementDate(kardexDetails.getMovementDate());
                    kardex.setComments(kardexDetails.getComments());
                    return kardexRepository.save(kardex);
                })
                .orElseThrow(() -> new RuntimeException("Kardex no encontrado con id: " + id));
    }

    // Eliminar un movimiento
    public void deleteKardex(String id) {
        kardexRepository.deleteById(id);
    }


}