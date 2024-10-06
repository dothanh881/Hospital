package com.hospital.hospitalmanagement.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nurse")
public class NurseEntity extends  EmployeeEntity{



    @OneToMany(mappedBy = "nurse")
    private List<AdmissionEntity> admissions;

    public NurseEntity(List<AdmissionEntity> admissions) {
        this.admissions = admissions;
    }

    public NurseEntity() {
    }

    public NurseEntity(String code, String firstName, String lastName, Date dob, String gender, String phoneNumber, String street, String specialty, int degreeYear, Cities city, Districts district, Wards ward, Date startDate, DepartmentEntity department, List<AdmissionEntity> admissions) {
        super(code, firstName, lastName, dob, gender, phoneNumber, street, specialty, degreeYear, city, district, ward, startDate, department);
        this.admissions = admissions;
    }

    public List<AdmissionEntity> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(List<AdmissionEntity> admissions) {
        this.admissions = admissions;
    }
}
