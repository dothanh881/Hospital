package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "doctor")
public class DoctorEntity extends EmployeeEntity {


    // Default constructor
    public DoctorEntity() {
        super();
    }



    @OneToMany(mappedBy = "doctor")
    private List<ExaminationEntity> examinations;

    @OneToMany(mappedBy = "doctor")
    private List<AdmissionEntity> admissions;

    // Getters and Setters


    public DoctorEntity(List<ExaminationEntity> examinations, List<AdmissionEntity> admissions) {
        this.examinations = examinations;
        this.admissions = admissions;
    }

    public DoctorEntity(String code, String firstName, String lastName, Date dob, String gender, String phoneNumber, String street, String specialty, int degreeYear, Cities city, Districts district, Wards ward, Date startDate, DepartmentEntity department, List<ExaminationEntity> examinations, List<AdmissionEntity> admissions) {
        super(code, firstName, lastName, dob, gender, phoneNumber, street, specialty, degreeYear, city, district, ward, startDate, department);
        this.examinations = examinations;
        this.admissions = admissions;
    }

    public List<AdmissionEntity> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(List<AdmissionEntity> admissions) {
        this.admissions = admissions;
    }

    public List<ExaminationEntity> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<ExaminationEntity> examinations) {
        this.examinations = examinations;
    }

}