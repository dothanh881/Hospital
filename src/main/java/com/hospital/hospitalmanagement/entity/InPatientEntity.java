package com.hospital.hospitalmanagement.entity;

import com.hospital.hospitalmanagement.entity.IdClass.InPatientId;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inpatient")
public class InPatientEntity {

    @Id  // 'inpatient_id' as the primary key
    @Column(name = "inpatient_id", nullable = false)
    private Integer inpatientId;  // Primary Key, which is also the Foreign Key referring to PatientEntity

    @ManyToOne
    @JoinColumn(name = "inpatient_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)  // Foreign key to PatientEntity
    private PatientEntity patient;  // Reference to PatientEntity, using the inpatient_id


//    @Id
//    @ManyToOne
//    @JoinColumn(name = "inpatient_id", referencedColumnName = "id", nullable = false)
//    private PatientEntity patient; // inpatient_id also acts as FK


    @Column(nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "inPatient")
    private List<AdmissionEntity> admissions;

//    public InPatientEntity(Integer id, PatientEntity patient, String code, List<AdmissionEntity> admissions) {
//        this.id = id;
//        this.patient = patient;
//        this.code = code;
//        this.admissions = admissions;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }



       public Integer getInpatientId() {
        return inpatientId;
    }

    public void setInpatientId(Integer inpatientId) {
        this.inpatientId = inpatientId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
        if (patient != null) {
            this.inpatientId = patient.getID();  // Set the inpatientId from the Patient's ID
        }
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
