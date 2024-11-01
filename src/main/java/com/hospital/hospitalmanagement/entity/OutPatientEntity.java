package com.hospital.hospitalmanagement.entity;

import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "outpatient")
public class OutPatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This makes the 'id' auto-increment
    @Column(name = "id", nullable = false)
    private Integer id; // New Primary Key

    @OneToOne // This establishes a one-to-one relationship with PatientEntity
    @JoinColumn(name = "outpatient_id", referencedColumnName = "id", nullable = false)
    private PatientEntity patient; // Reference to PatientEntity

    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "outPatient")
    private List<ExaminationEntity> examinations;

    // Constructors, getters, and setters

    public OutPatientEntity() {
    }

    // Getter and setter for the new id field
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ExaminationEntity> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<ExaminationEntity> examinations) {
        this.examinations = examinations;
    }
}