package com.hospital.hospitalmanagement.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "treatment_medication")
public class TreatmentMedicationEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "treatment_Id")
    private TreatmentEntity treatment;

    @ManyToOne
    @JoinColumn(name = "medication_Id")
    private MedicationEntity medication;

    @Column
    private Integer quantity;

    @Column(precision = 10)
    private BigDecimal price;



    public TreatmentMedicationEntity(TreatmentEntity treatment, MedicationEntity medication, Integer quantity, BigDecimal price) {
        this.treatment = treatment;
        this.medication = medication;
        this.quantity = quantity;
        this.price = price;

    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void setID(int ID) {
        this.ID = ID;
    }




    public TreatmentMedicationEntity() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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
