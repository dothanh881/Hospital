package com.hospital.hospitalmanagement.models.dto;

import java.math.BigDecimal;

public class ExaminationMedicationDTO {
    private Integer examinationId;
    private Integer medicationId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total;

    // Getters and Setters

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    // Method to calculate the total based on price and quantity
    public void calculateTotal() {
        if (price != null && quantity != null) {
            this.total = price.multiply(new BigDecimal(quantity));
        }
    }
}
