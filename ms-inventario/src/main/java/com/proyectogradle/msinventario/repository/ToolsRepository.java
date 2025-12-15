package com.proyectogradle.msinventario.repository;


import com.proyectogradle.msinventario.entity.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ToolsRepository extends JpaRepository<Tools, String> {
}
