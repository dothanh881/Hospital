package com.hospital.hospitalmanagement.models.dto;

import java.util.Date;

public class OutPatientWithExaminationDTO {
    // OutPatient fields
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String address;
    private String phoneNumber;
    private String visitReason;

    // Examination fields
    private Date examinationDate;
    private String diagnosis;
    private double fee;

    public OutPatientWithExaminationDTO(String firstName, String lastName, Date dob, String gender, String address, String phoneNumber, String visitReason, Date examinationDate, String diagnosis, double fee) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.visitReason = visitReason;
        this.examinationDate = examinationDate;
        this.diagnosis = diagnosis;
        this.fee = fee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
