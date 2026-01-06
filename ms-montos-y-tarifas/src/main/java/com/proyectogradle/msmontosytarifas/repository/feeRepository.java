package com.proyectogradle.msmontosytarifas.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.proyectogradle.msmontosytarifas.enitity.fee;

import java.util.List;
import java.util.Optional;

@Repository
public interface feeRepository extends JpaRepository<fee, String> {
    Optional<fee> findByType(String type);
}