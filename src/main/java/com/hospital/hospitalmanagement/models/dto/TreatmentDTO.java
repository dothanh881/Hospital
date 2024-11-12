package com.hospital.hospitalmanagement.models.dto;

import java.util.Date;
import java.util.List;

public class TreatmentDTO {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String medications;
    private TreatmentStatusDTO treatmentStatus;
    private Integer statusId;  // Renamed from statudId to statusId for consistency
    private Integer admissionId;
    private List<TreatmentMedicationDTO> treatmentMedications; // Updated to List

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Integer getStatusId() {  // Renamed getter method
        return statusId;
    }

    public void setStatusId(Integer statusId) {  // Renamed setter method
        this.statusId = statusId;
    }

    public Integer getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(Integer admissionId) {
        this.admissionId = admissionId;
    }

    public List<TreatmentMedicationDTO> getTreatmentMedications() {  // Updated getter method
        return treatmentMedications;
    }

    public void setTreatmentMedications(List<TreatmentMedicationDTO> treatmentMedications) {  // Updated setter method
        this.treatmentMedications = treatmentMedications;
    }
}
