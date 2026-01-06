package com.proyectogradle.msclientes.dto;

import java.time.Instant;

public class CustomerDTO {
    private String id;
    private String name;
    private String rut;
    private String phone;
    private String email;
    private String status;
    private Instant createdAt;

    // Constructor vacío
    public CustomerDTO() {
    }

    // Constructor con todos los campos
    public CustomerDTO(String id, String name, String rut, String phone, String email, String status, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.rut = rut;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }


}
