package com.proyectogradle.msprestamos.DTO;

import java.io.Serializable;

public class InventoryMovementDTO implements Serializable {
    private String toolId;
    private int quantity; // -1 para préstamo
    private String type;  // "LOAN"
    private String loanId;

    // Constructor vacío
    public InventoryMovementDTO() {}

    // Constructor completo
    public InventoryMovementDTO(String toolId, int quantity, String type, String loanId) {
        this.toolId = toolId;
        this.quantity = quantity;
        this.type = type;
        this.loanId = loanId;
    }

    // Getters y Setters (Obligatorios para que Jackson convierta a JSON)
    public String getToolId() { return toolId; }
    public void setToolId(String toolId) { this.toolId = toolId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }


    public String getLoanId() { return loanId; }
    public void setLoanId(String loanId) { this.loanId = loanId; }
}