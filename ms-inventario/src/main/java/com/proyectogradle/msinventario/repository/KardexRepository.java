package com.proyectogradle.msinventario.repository;

import com.proyectogradle.msinventario.entity.Kardex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KardexRepository extends JpaRepository<Kardex, String> {

}
