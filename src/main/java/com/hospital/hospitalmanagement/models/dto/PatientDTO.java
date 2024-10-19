package com.hospital.hospitalmanagement.models.dto;

import java.util.Date;

public class PatientDTO {

    private Integer Id;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String street;
    private Integer cityId, districtId, wardId;
    private String phoneNumber;

    // No-argument constructor
    public PatientDTO() {
    }


    public PatientDTO(Integer id, String firstName, String lastName, Date dob, String gender, String street, Integer cityId, Integer districtId, Integer wardId, String phoneNumber) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.street = street;
        this.cityId = cityId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }



    // Constructor with all parameters

    // Getters and setters...


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



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
