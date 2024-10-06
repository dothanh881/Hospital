package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "admission")
public class AdmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inpatient_Id", nullable = false)
    private InPatientEntity inPatient;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_Id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_Id", nullable = false)
    private NurseEntity nurse;

    @OneToMany(mappedBy = "admission")
    private List<TreatmentEntity> treatments;
    @Column(name = "date_admission", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAdmission;

    @Column(name = "diagnosis", length = 255)
    private String diagnosis;

    @Column(name = "sickroom", length = 100)
    private String sickroom;

    @Column(name = "dateOfDischarge")
    @Temporal(TemporalType.DATE)
    private Date dateOfDischarge;

    @Column(precision = 10, scale = 2)
    private BigDecimal fee;

    public AdmissionEntity(InPatientEntity inPatient, DoctorEntity doctor, NurseEntity nurse, List<TreatmentEntity> treatments, Date dateAdmission, String diagnosis, String sickroom, Date dateOfDischarge, BigDecimal fee) {
        this.inPatient = inPatient;
        this.doctor = doctor;
        this.nurse = nurse;
        this.treatments = treatments;
        this.dateAdmission = dateAdmission;
        this.diagnosis = diagnosis;
        this.sickroom = sickroom;
        this.dateOfDischarge = dateOfDischarge;
        this.fee = fee;
    }

    public AdmissionEntity() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public InPatientEntity getInPatient() {
        return inPatient;
    }

    public void setInPatient(InPatientEntity inPatient) {
        this.inPatient = inPatient;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public NurseEntity getNurse() {
        return nurse;
    }

    public void setNurse(NurseEntity nurse) {
        this.nurse = nurse;
    }

    public List<TreatmentEntity> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<TreatmentEntity> treatments) {
        this.treatments = treatments;
    }

    public Date getDateAdmission() {
        return dateAdmission;
    }

    public void setDateAdmission(Date dateAdmission) {
        this.dateAdmission = dateAdmission;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSickroom() {
        return sickroom;
    }

    public void setSickroom(String sickroom) {
        this.sickroom = sickroom;
    }

    public Date getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(Date dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}