package com.proyectogradle.msclientes.controller;

import com.proyectogradle.msclientes.entity.Customer;
import com.proyectogradle.msclientes.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(service.save(customer));
    }

  

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // RF3.2: Endpoint para restringir o activar cliente
    @PatchMapping("/{id}/status")
    public ResponseEntity<Customer> changeStatus(
            @PathVariable String id,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}