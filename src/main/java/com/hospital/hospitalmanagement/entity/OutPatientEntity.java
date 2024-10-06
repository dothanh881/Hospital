package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "outpatient")
public class OutPatientEntity extends PatientEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "outPatient")
    private List<ExaminationEntity> examinations;

    public OutPatientEntity() {

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

    public OutPatientEntity(String firstName, String lastName, Date dateOfBirth, String gender, String street, String phoneNumber, Cities city, Districts district, Wards ward, String code, List<ExaminationEntity> examinations) {
        super(firstName, lastName, dateOfBirth, gender, street, phoneNumber, city, district, ward);
        this.code = code;
        this.examinations = examinations;
    }

    public OutPatientEntity(String code, List<ExaminationEntity> examinations) {
        this.code = code;
        this.examinations = examinations;
    }

    public OutPatientEntity(List<ExaminationEntity> examinations) {
        this.examinations = examinations;
    }
}
