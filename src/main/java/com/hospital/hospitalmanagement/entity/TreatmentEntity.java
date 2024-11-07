package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "treatment")
public class TreatmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;


    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;




    // Many treatments can have the same TreatmentStatusEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)  // Foreign key to TreatmentStatusEntity
    private TreatmentStatusEntity treatmentStatusEntity;
    @Column(name = "medications", length = 255)
    private String medications;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admission_Id", nullable = false)
    private AdmissionEntity admission;

    @OneToMany(mappedBy = "treatment")
    private List<TreatmentMedicationEntity>  treatmentMedication;


    public TreatmentEntity() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public TreatmentStatusEntity getTreatmentStatusEntity() {
        return treatmentStatusEntity;
    }

    public void setTreatmentStatusEntity(TreatmentStatusEntity treatmentStatusEntity) {
        this.treatmentStatusEntity = treatmentStatusEntity;
    }

    public TreatmentEntity(Integer ID, Date startDate, Date endDate, TreatmentStatusEntity treatmentStatusEntity, String medications, AdmissionEntity admission, List<TreatmentMedicationEntity> treatmentMedication) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.treatmentStatusEntity = treatmentStatusEntity;
        this.medications = medications;
        this.admission = admission;
        this.treatmentMedication = treatmentMedication;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public AdmissionEntity getAdmission() {
        return admission;
    }

    public void setAdmission(AdmissionEntity admission) {
        this.admission = admission;
    }

    public List<TreatmentMedicationEntity> getTreatmentMedication() {
        return treatmentMedication;
    }

    public void setTreatmentMedication(List<TreatmentMedicationEntity> treatmentMedication) {
        this.treatmentMedication = treatmentMedication;
    }
}
