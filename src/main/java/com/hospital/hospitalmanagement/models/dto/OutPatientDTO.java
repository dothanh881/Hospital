package com.hospital.hospitalmanagement.models.dto;

import java.util.Date;

public class OutPatientDTO extends PatientDTO {

    private String visitReason;

    // Constructor with all parameters
    public OutPatientDTO(Long id, String code, String firstName, String lastName, Date dob, String gender, String address, String phoneNumber, String visitReason) {
        super(id, code, firstName, lastName, dob, gender, address, phoneNumber);  // Pass parameters to the parent constructor
        this.visitReason = visitReason;
    }

    // No-argument constructor (optional, useful for frameworks like Hibernate)
    public OutPatientDTO() {
        super();  // Calls the no-arg constructor of PatientDTO
    }

    // Getters and Setters
    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }
}
