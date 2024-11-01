package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inpatient")
public class InPatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This makes the 'id' auto-increment
    @Column(name = "id", nullable = false)
    private Integer id; // New Primary Key


    @OneToOne // This establishes a one-to-one relationship with PatientEntity
    @JoinColumn(name = "inpatient_id", referencedColumnName = "id", nullable = false)
    private PatientEntity patient; // Reference to PatientEntity



    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "inPatient")
    private List<AdmissionEntity> admissions;

    public InPatientEntity(Integer id, PatientEntity patient, String code, List<AdmissionEntity> admissions) {
        this.id = id;
        this.patient = patient;
        this.code = code;
        this.admissions = admissions;
    }

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

    // Default constructor
    public InPatientEntity() {
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<AdmissionEntity> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(List<AdmissionEntity> admissions) {
        this.admissions = admissions;
    }
}
