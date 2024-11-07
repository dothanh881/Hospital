package com.hospital.hospitalmanagement.models.dto;

import com.hospital.hospitalmanagement.entity.TreatmentStatusEntity;

import java.util.Date;

public class TreatmentDTO {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String medications;
    private TreatmentStatusDTO treatmentStatus;

    private AdmissionDTO admission;
    private TreatmentMedicationDTO treatmentMedication;

    public TreatmentDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TreatmentStatusDTO getTreatmentStatus() {
        return treatmentStatus;
    }

    public void setTreatmentStatus(TreatmentStatusDTO treatmentStatus) {
        this.treatmentStatus = treatmentStatus;
    }

    public AdmissionDTO getAdmission() {
        return admission;
    }

    public void setAdmission(AdmissionDTO admission) {
        this.admission = admission;
    }

    public TreatmentMedicationDTO getTreatmentMedication() {
        return treatmentMedication;
    }

    public void setTreatmentMedication(TreatmentMedicationDTO treatmentMedication) {
        this.treatmentMedication = treatmentMedication;
    }
}
