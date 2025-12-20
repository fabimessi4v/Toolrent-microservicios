package com.proyectogradle.msprestamos.DTO;

public class CustomerDTO {
    private String id;
    private String name;
    private int unpaidFines; // 0: Sin deuda, 1: Con deuda

    // Constructores
    public CustomerDTO() {}

    public CustomerDTO(String id, String name, int unpaidFines) {
        this.id = id;
        this.name = name;
        this.unpaidFines = unpaidFines;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getUnpaidFines() { return unpaidFines; }
    public void setUnpaidFines(int unpaidFines) { this.unpaidFines = unpaidFines; }
}