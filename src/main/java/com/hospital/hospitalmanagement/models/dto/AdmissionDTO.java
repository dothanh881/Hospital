package com.hospital.hospitalmanagement.models.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AdmissionDTO {

    private Integer id;               // AI PK, optional for updates
    private Integer inpatientId;      // ID of the patient
    private Integer doctorId;         // ID of the doctor
    private Integer nurseId;          // ID of the nurse
    private Date dateAdmission;       // Date of admission
    private Date dateOfDischarge;     // Date of discharge
    private Integer sickroom;         // Room number
    private String diagnosis;          // Diagnosis description
    private BigDecimal fee;


    public AdmissionDTO() {
    }

    public AdmissionDTO(Integer id, Integer inpatientId, Integer doctorId, Integer nurseId, Date dateAdmission, Date dateOfDischarge, Integer sickroom, String diagnosis, BigDecimal fee) {
        this.id = id;
        this.inpatientId = inpatientId;
        this.doctorId = doctorId;
        this.nurseId = nurseId;
        this.dateAdmission = dateAdmission;
        this.dateOfDischarge = dateOfDischarge;
        this.sickroom = sickroom;
        this.diagnosis = diagnosis;
        this.fee = fee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInpatientId() {
        return inpatientId;
    }

    public void setInpatientId(Integer inpatientId) {
        this.inpatientId = inpatientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getNurseId() {
        return nurseId;
    }

    public void setNurseId(Integer nurseId) {
        this.nurseId = nurseId;
    }

    public Date getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(Date dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public Date getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(Date dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    public Integer getSickroom() {
        return sickroom;
    }

    public void setSickroom(Integer sickroom) {
        this.sickroom = sickroom;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
