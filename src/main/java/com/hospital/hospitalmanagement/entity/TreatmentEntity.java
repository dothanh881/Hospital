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

    @Column(name = "result", length = 255)
    private String result;

    @Column(name = "medications", length = 255)
    private String medications;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admission_Id", nullable = false)
    private AdmissionEntity admission;

    @OneToMany(mappedBy = "treatment")
    private List<TreatmentMedicationEntity>  treatmentMedication;

    public TreatmentEntity(  Date startDate, Date endDate, String result, String medications, AdmissionEntity admission, List<TreatmentMedicationEntity> treatmentMedication) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.result = result;
        this.medications = medications;
        this.admission = admission;
        this.treatmentMedication = treatmentMedication;
    }

    public TreatmentEntity() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
