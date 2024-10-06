package com.hospital.hospitalmanagement.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "treatment_medication")
public class TreatmentMedicationEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne
    @JoinColumn(name = "treatment_Id")
    private TreatmentEntity treatment;

    @ManyToOne
    @JoinColumn(name = "medication_Id")
    private MedicationEntity medication;

    @Column
    private int quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    public TreatmentMedicationEntity(TreatmentEntity treatment, MedicationEntity medication, int quantity, BigDecimal price, BigDecimal total) {
        this.treatment = treatment;
        this.medication = medication;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setID(int ID) {
        this.ID = ID;
    }




    public TreatmentMedicationEntity() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public TreatmentEntity getTreatment() {
        return treatment;
    }

    public void setTreatment(TreatmentEntity treatment) {
        this.treatment = treatment;
    }

    public MedicationEntity getMedication() {
        return medication;
    }

    public void setMedication(MedicationEntity medication) {
        this.medication = medication;
    }
}
