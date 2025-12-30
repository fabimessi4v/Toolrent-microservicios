package com.proyectogradle.msinventario.controller;

import com.proyectogradle.msinventario.entity.Tools;
import com.proyectogradle.msinventario.service.InventoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // Indica que esta clase maneja peticiones HTTP
@RequestMapping("/api/tools") // La dirección base será http://localhost:8080/api/tools
public class ToolsController {

    private final InventoryService inventoryService;

    // Constructor manual (Inyección de Dependencias sin Lombok)
    public ToolsController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // 1. Endpoint para CREAR (POST)
    @PostMapping
    public ResponseEntity<?> createTool(@RequestBody Tools tool) {
        try {
            Tools newTool = inventoryService.createTool(tool);
            // Retorna un 201 Created y el objeto creado
            return new ResponseEntity<>(newTool, HttpStatus.CREATED);

        } catch (IllegalStateException e) {
            // Error de autenticación (no pudo obtener usuario mock)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            // Cualquier otro error interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la herramienta: " + e.getMessage());
        }
    }

    // 2. Endpoint para BORRAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTool(@PathVariable String id) {
        try {
            boolean isDeleted = inventoryService.deleteTool(id);

            if (isDeleted) {
                // Retorna 200 OK
                return ResponseEntity.ok("Herramienta eliminada correctamente.");
            } else {
                // Retorna 404 Not Found si el ID no existe
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la herramienta con ID: " + id);
            }

        } catch (SecurityException e) {
            // ¡Aquí atrapamos tu regla de negocio!
            // Si el usuario NO es ADMIN, retornamos 403 Forbidden
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    // Simulación de Base de datos en memoria para probar rápido
    private int stockSimulado = 5;

    // Endpoint que llama tu RF2.2
    // responde si hay stock (>0) (true o false)
    @GetMapping("/{id}/availability")
    public boolean checkAvailability(@PathVariable String id) {
        System.out.println("MS1: Verificando stock de la herramienta " + id);
        return stockSimulado > 0;
    }

    // Endpoint que llama tu RF2.1 y RF2.3
    @PostMapping("/movement")
    public void registerMovement(@RequestBody Map<String, Object> payload) {
        String type = (String) payload.get("type");
        System.out.println("MS1: Recibido movimiento tipo: " + type);

        if ("LOAN".equals(type)) {
            stockSimulado--;
            System.out.println("MS1: Stock descontado. Nuevo stock: " + stockSimulado);
        } else if ("RETURN".equals(type)) {
            stockSimulado++;
            System.out.println("MS1: Stock aumentado. Nuevo stock: " + stockSimulado);
        }
    }

    // 3. Endpoint para OBTENER TODAS (GET ALL)
    @GetMapping
    public ResponseEntity<List<Tools>> getAllTools() {
        try {
            List<Tools> tools = inventoryService.getAllTools();
            if (tools.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tools, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }











}