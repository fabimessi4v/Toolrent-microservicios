package com.proyectogradle.msinventario.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "kardex")
public class Kardex {

    @Id
    private String id;

    /* ✅ RELACIÓN INTERNA: Como Tools vive en este mismo microservicio,
    podemos mantener el @ManyToOne físico para integridad referencial DENTRO del MS.*/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tool_id", nullable = false)
    private Tools tool;

    // ✂️ RELACIÓN EXTERNA (Corte): Ya no es un objeto User. Es solo el ID.
    @Column(name = "user_id", nullable = false)
    private String userId;

    // ✂️ RELACIÓN EXTERNA (Corte): Ya no es un objeto Loans. Es solo el ID.
    @Column(name = "loans_id") // nullable = true
    private String loanId;

    private String type; // "Registro nuevo", "Baja", etc.

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "movement_date")
    private LocalDate movementDate;

    @Column(name = "comments")
    private String comments;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    //getters y Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tools getTool() {
        return tool;
    }

    public void setTool(Tools tool) {
        this.tool = tool;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
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
}