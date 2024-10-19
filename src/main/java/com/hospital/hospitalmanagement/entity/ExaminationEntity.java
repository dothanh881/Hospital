package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "examination")
public class ExaminationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outpatient_id", nullable = false)
    private OutPatientEntity outPatient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_Id", nullable = false)
    private DoctorEntity doctor;

    @Temporal(TemporalType.DATE)
    @Column(name = "examination_Date", nullable = false)
    private Date examinationDate;

    @Column(nullable = false)
    private String diagnosis;

    @Temporal(TemporalType.DATE)
    @Column(name = "next_ExaminationDate")
    private Date nextExaminationDate;

    @Column(length = 255)
    private String medications;

    @Column(precision = 10, scale = 2)
    private BigDecimal fee;


    @OneToMany(mappedBy = "examination")
    private List<ExaminationMedicationEntity> examinationMedications = new ArrayList<>();

    public ExaminationEntity() {
    }
    public ExaminationEntity(OutPatientEntity outPatient, DoctorEntity doctor, Date examinationDate, String diagnosis, Date nextExaminationDate, String medications, BigDecimal fee) {
        this.outPatient = outPatient;
        this.doctor = doctor;
        this.examinationDate = examinationDate;
        this.diagnosis = diagnosis;
        this.nextExaminationDate = nextExaminationDate;
        this.medications = medications;
        this.fee = fee;
    }

    public OutPatientEntity getOutPatient() {
        return outPatient;
    }

    public void setOutPatient(OutPatientEntity outPatient) {
        this.outPatient = outPatient;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public List<ExaminationMedicationEntity> getExaminationMedications() {
        return examinationMedications;
    }

    public void setExaminationMedications(List<ExaminationMedicationEntity> examinationMedications) {
        this.examinationMedications = examinationMedications;
    }
}
