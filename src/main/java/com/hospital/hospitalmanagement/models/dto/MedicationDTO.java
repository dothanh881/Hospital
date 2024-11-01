package com.hospital.hospitalmanagement.models.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MedicationDTO {
    private Integer ID; // Medication ID
    private String code;                     // Medication Code
    private String name;                     // Medication Name
    private String effect;                   // Medication Effect
    private BigDecimal price;                // Medication Price
    private Date expirationDate;

    public MedicationDTO() {
    }

    public MedicationDTO(Integer ID, String code, String name, String effect, BigDecimal price, Date expirationDate) {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.effect = effect;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
