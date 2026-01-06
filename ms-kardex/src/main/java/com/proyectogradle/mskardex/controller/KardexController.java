package com.proyectogradle.mskardex.controller;

import com.proyectogradle.mskardex.entity. Kardex;
import com. proyectogradle.mskardex.services.KardexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework. http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util. List;

@RestController
@RequestMapping("/api") // ← Cambiado de "/api/kardex" a "/api"
public class KardexController {

    @Autowired
    private KardexService kardexService;

    // Obtener todos los movimientos
    @GetMapping("/kardex") // ← Ahora la ruta completa es /api/kardex
    public ResponseEntity<List<Kardex>> getAllKardex() {
        return ResponseEntity.ok(kardexService.getAllKardex());
    }
}
