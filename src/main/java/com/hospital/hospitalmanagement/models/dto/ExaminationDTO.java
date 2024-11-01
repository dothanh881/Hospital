package com.hospital.hospitalmanagement.models.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ExaminationDTO {

    private Integer id;
    private Integer outPatientId;
    private Integer doctorId;
    private Date examinationDate;
    private String diagnosis;
    private Date nextExaminationDate;
    private List<ExaminationMedicationDTO> medications; // List of Medications
    private BigDecimal fee;

    // No-arg constructor
    public ExaminationDTO() {
    }

    public ExaminationDTO(Integer id, Integer outPatientId, Integer doctorId, Date examinationDate, String diagnosis, Date nextExaminationDate, List<ExaminationMedicationDTO> medications, BigDecimal fee) {
        this.id = id;
        this.outPatientId = outPatientId;
        this.doctorId = doctorId;
        this.examinationDate = examinationDate;
        this.diagnosis = diagnosis;
        this.nextExaminationDate = nextExaminationDate;
        this.medications = medications;
        this.fee = fee;
    }

    public List<ExaminationMedicationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<ExaminationMedicationDTO> medications) {
        this.medications = medications;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOutPatientId() {
        return outPatientId;
    }

    public void setOutPatientId(Integer outPatientId) {
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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }



    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    // toString method for debugging

}
