package com.proyectogradle.mskardex.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "kardex")
public class Kardex {

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "tool_id", length = 36, nullable = false)
    private String toolId;

    @Column(name = "user_id", length = 36, nullable = false)
    private String userId;

    @Column(name = "loans_id", length = 36)
    private String loansId;

    @Column(name = "type", nullable = false, columnDefinition = "TINYTEXT")
    private String type;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "movement_date", nullable = false)
    private LocalDate movementDate;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "createdAt", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // Constructor vacío (requerido por JPA)
    public Kardex() {
    }

    // Constructor con parámetros (opcional, útil para crear instancias)
    public Kardex(String id, String toolId, String userId, String loansId,
                  String type, Integer quantity, LocalDate movementDate, String comments) {
        this.id = id;
        this.toolId = toolId;
        this.userId = userId;
        this.loansId = loansId;
        this.type = type;
        this.quantity = quantity;
        this.movementDate = movementDate;
        this.comments = comments;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoansId() {
        return loansId;
    }

    public void setLoansId(String loansId) {
        this.loansId = loansId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDate movementDate) {
        this.movementDate = movementDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // toString (opcional, útil para debugging)
    @Override
    public String toString() {
        return "Kardex{" +
                "id='" + id + '\'' +
                ", toolId='" + toolId + '\'' +
                ", userId='" + userId + '\'' +
                ", loansId='" + loansId + '\'' +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                ", movementDate=" + movementDate +
                ", comments='" + comments + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}