package com.hospital.hospitalmanagement.models.dto;

import java.math.BigDecimal;

public class TreatmentMedicationDTO {
    private Integer admissionId;
    private Integer medicationId;
    private BigDecimal price;
    private Integer quantity;


    // Getters and Setters

    public TreatmentMedicationDTO(Integer admissionId, Integer medicationId, BigDecimal price, Integer quantity) {
        this.admissionId = admissionId;
        this.medicationId = medicationId;
        this.price = price;
        this.quantity = quantity;
    }

    public TreatmentMedicationDTO() {
    }

    public Integer getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Integer admissionId) {
        this.admissionId = admissionId;
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
}
