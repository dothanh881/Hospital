package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inpatient")
public class InPatientEntity extends PatientEntity {
    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "inPatient")
    private List<AdmissionEntity> admissions;

    public InPatientEntity(String firstName, String lastName, Date dateOfBirth, String gender, String street, String phoneNumber, Cities city, Districts district, Wards ward, String code, List<AdmissionEntity> admissions) {
        super(firstName, lastName, dateOfBirth, gender, street, phoneNumber, city, district, ward);
        this.code = code;
        this.admissions = admissions;
    }

    public InPatientEntity(String code, List<AdmissionEntity> admissions) {
        this.code = code;
        this.admissions = admissions;
    }
    // Default constructor
    public InPatientEntity() {
        super();
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
