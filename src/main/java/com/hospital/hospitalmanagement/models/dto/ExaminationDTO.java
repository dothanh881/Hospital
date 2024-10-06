package com.hospital.hospitalmanagement.models.dto;

import java.util.Date;

public class ExaminationDTO {

    private Long id;
    private Long outPatientId;
    private Date examinationDate;
    private String diagnosis;
    private Date nextExaminationDate;
    private String medications;
    private Double fee;

    // No-arg constructor
    public ExaminationDTO() {
    }

    // Constructor with all fields
    public ExaminationDTO(Long id, Long outPatientId, Date examinationDate, String diagnosis, Date nextExaminationDate, String medications, Double fee) {
        this.id = id;
        this.outPatientId = outPatientId;

        this.examinationDate = examinationDate;
        this.diagnosis = diagnosis;
        this.nextExaminationDate = nextExaminationDate;
        this.medications = medications;
        this.fee = fee;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutPatientId() {
        return outPatientId;
    }

    public void setOutPatientId(Long outPatientId) {
        this.outPatientId = outPatientId;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getNextExaminationDate() {
        return nextExaminationDate;
    }

    public void setNextExaminationDate(Date nextExaminationDate) {
        this.nextExaminationDate = nextExaminationDate;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "ExaminationDTO{" +
                "id=" + id +
                ", outPatientId=" + outPatientId +
                ", examinationDate=" + examinationDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", nextExaminationDate=" + nextExaminationDate +
                ", medications='" + medications + '\'' +
                ", fee=" + fee +
                '}';
    }
}
