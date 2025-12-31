package com.proyectogradle.msinventario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tools")
public class Tools {

    @Id
    private String id;
    private String name;
    @Column(name="category", length = 50)
    private String category;

    @Column(name="status", columnDefinition = "TINYTEXT")
    private String status;

    @Column(name = "replacement_value", precision = 10, scale = 2)
    private BigDecimal replacementValue;

    @Column(name = "rental_price", precision = 10, scale = 2)
    private BigDecimal rentalPrice;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "tool_image_url")
    private String toolImageUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // NOTA: Aquí NO ponemos @OneToMany(mappedBy="tool") hacia Kardex

//getters y Setters
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getToolImageUrl() {
        return toolImageUrl;
    }

    public void setToolImageUrl(String toolImageUrl) {
        this.toolImageUrl = toolImageUrl;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public BigDecimal getReplacementValue() {
        return replacementValue;
    }

    public void setReplacementValue(BigDecimal replacementValue) {
        this.replacementValue = replacementValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
