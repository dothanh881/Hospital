package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.math.BigDecimal;


@Entity
@Table(name = "examination_medication")
public class ExaminationMedicationEntity {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer ID;

        @ManyToOne
        @JoinColumn(name = "examination_Id")
        private ExaminationEntity examination;

        @ManyToOne
        @JoinColumn(name = "medication_Id")
        private MedicationEntity medication;

    @Column
    private int quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;



    public ExaminationMedicationEntity(ExaminationEntity examination, MedicationEntity medication, int quantity, BigDecimal price) {
        this.examination = examination;
        this.medication = medication;
        this.quantity = quantity;
        this.price = price;

    }

    public ExaminationMedicationEntity() {
    }

    // Getters and Setters


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public ExaminationEntity getExamination() {
        return examination;
    }

    public void setExamination(ExaminationEntity examination) {
        this.examination = examination;
    }

    public MedicationEntity getMedication() {
        return medication;
    }

    public void setMedication(MedicationEntity medication) {
        this.medication = medication;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
