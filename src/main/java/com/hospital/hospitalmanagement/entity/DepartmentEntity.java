package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "doctor_Id", referencedColumnName = "id", nullable = false)
    private DoctorEntity headDoctor;



    @OneToMany(mappedBy = "department")
    private List<EmployeeEntity> employees;




    public DepartmentEntity(String code, String title, DoctorEntity headDoctor, List<EmployeeEntity> employees) {
        this.code = code;
        this.title = title;
        this.headDoctor = headDoctor;
        this.employees = employees;
    }

    public DepartmentEntity() {

    }

    // Getters and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DoctorEntity getHeadDoctor() {
        return headDoctor;
    }

    public void setHeadDoctor(DoctorEntity headDoctor) {
        this.headDoctor = headDoctor;
    }


}
