package com.hospital.hospitalmanagement.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "medication_import")
public class MedicationImportEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;


    @Column(name = "import_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date importDate;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "medication_Id")
    private MedicationEntity medicationImport;

    @ManyToOne
    @JoinColumn(name = "provider_Id")
    private ProviderEntity providerImport;

    public MedicationImportEntity(Date importDate, BigDecimal price, int quantity, MedicationEntity medicationImport, ProviderEntity providerImport) {
        this.importDate = importDate;
        this.price = price;
        this.quantity = quantity;
        this.medicationImport = medicationImport;
        this.providerImport = providerImport;
    }

    public MedicationImportEntity() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MedicationEntity getMedicationImport() {
        return medicationImport;
    }

    public void setMedicationImport(MedicationEntity medicationImport) {
        this.medicationImport = medicationImport;
    }

    public ProviderEntity getProviderImport() {
        return providerImport;
    }

    public void setProviderImport(ProviderEntity providerImport) {
        this.providerImport = providerImport;
    }
}
