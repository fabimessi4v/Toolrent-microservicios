package com.proyectogradle.msmontosytarifas.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "fee")
public class fee {
    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "customers_id", nullable = false, length = 36)
    private String customerId; // UUID del cliente (MS-Clientes)

    @Column(name = "loans_id", nullable = false, length = 36)
    private String loanId; // UUID del préstamo (MS-Loans)

    @Column(name = "type", nullable = false)
    private String type; // "LATE_FEE", "REPLACEMENT_CHARGE", "RENT_CHARGE"

    @Column(name = "value", nullable = false)
    private Float value;

    @Column(name = "createdAt")
    private Instant createdAt;

    // Getters y Setters manuales


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
