package com.proyectogradle.msclientes.services;

import com.proyectogradle.msclientes.dto.CustomerDTO;
import com.proyectogradle.msclientes.entity.Customer;
import com.proyectogradle.msclientes.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Customer save(Customer customer) {
        // RF3.1: Asignar ID único y estado inicial
        if (customer.getId() == null) {
            customer.setId(UUID.randomUUID().toString());
        }
        customer.setStatus("ACTIVO");
        return repository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Transactional
    public Customer updateStatus(String id, String newStatus) {
        // RF3.2: Cambiar estado del cliente
        Customer customer = findById(id);
        customer.setStatus(newStatus.toUpperCase());
        return repository.save(customer);
    }
    // ✅ NUEVO: Obtener todos los customers como DTO
    public List<CustomerDTO> getAllCustomersDTO() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    // Método auxiliar para convertir Entity a DTO
    private CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getRut(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getStatus(),
                customer.getCreatedAt()
        );
    }



}