package com.hospital.hospitalmanagement.entity;

import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "outpatient")
public class OutPatientEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // This makes the 'id' auto-increment
//    @Column(name = "id", nullable = false)
//    private Integer id; // New Primary Key
//
//    @OneToOne // This establishes a one-to-one relationship with PatientEntity
//    @JoinColumn(name = "outpatient_id", referencedColumnName = "id", nullable = false)
//    private PatientEntity patient; // Reference to PatientEntity
@Id  // 'inpatient_id' as the primary key
@Column(name = "outpatient_id", nullable = false)
private Integer outpatientId;  // Primary Key, which is also the Foreign Key referring to PatientEntity

    @ManyToOne
    @JoinColumn(name = "outpatient_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)  // Foreign key to PatientEntity
    private PatientEntity patient;  // Reference to PatientEntity, using the inpatient_id


    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "outPatient")
    private List<ExaminationEntity> examinations;

    // Constructors, getters, and setters

    public OutPatientEntity() {
    }

    // Getter and setter for the new id field



    public Integer getOutpatientId() {
        return outpatientId;
    }

    public void setOutpatientId(Integer outpatientId) {
        this.outpatientId = outpatientId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
        if (patient != null) {
            this.outpatientId = patient.getID();  // Set the inpatientId from the Patient's ID
        }
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