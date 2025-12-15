package com.proyectogradle.msinventario.DTO;

public class UserDTO {
    private String id;
    private String username;
    private String role;
    // Solo trae lo que necesitas para tu lógica de negocio


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
