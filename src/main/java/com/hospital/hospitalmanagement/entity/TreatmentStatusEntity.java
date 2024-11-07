package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "treatment_status")
public class TreatmentStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String name;


    @OneToMany(mappedBy = "treatmentStatusEntity")
    private List<TreatmentEntity> treatments;
    public TreatmentStatusEntity() {}

    public List<TreatmentEntity> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<TreatmentEntity> treatments) {
        this.treatments = treatments;
    }

    public TreatmentStatusEntity(Integer id, String code, String name, List<TreatmentEntity> treatments) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.treatments = treatments;
    }

    public TreatmentStatusEntity(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
