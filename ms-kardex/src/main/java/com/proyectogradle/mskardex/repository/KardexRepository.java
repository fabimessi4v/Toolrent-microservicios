package com.proyectogradle.mskardex. repository;

import com.proyectogradle.mskardex. entity.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java. util.List;

@Repository
public interface KardexRepository extends JpaRepository<Kardex, String> {

}