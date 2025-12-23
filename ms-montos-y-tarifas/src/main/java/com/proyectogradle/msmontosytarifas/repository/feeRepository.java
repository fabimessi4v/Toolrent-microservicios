package com.proyectogradle.msmontosytarifas.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.proyectogradle.msmontosytarifas.enitity.fee;
@Repository
public interface feeRepository extends JpaRepository<fee, String> {
    // Add custom query methods if needed, for example:
    // List<Fee> findByStatus(String status);
}