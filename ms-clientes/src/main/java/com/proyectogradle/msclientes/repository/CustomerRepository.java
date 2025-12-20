package com.proyectogradle.msclientes.repository;

import com.proyectogradle.msclientes.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    // Buscar por ID para validaciones de negocio
    Optional<Customer> findById(String id);
}