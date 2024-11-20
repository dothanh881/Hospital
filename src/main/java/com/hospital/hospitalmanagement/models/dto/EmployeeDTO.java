package com.hospital.hospitalmanagement.models.dto;

import java.util.Date;

public class EmployeeDTO {

        private String username;
        private String firstName;
        private String lastName;
        private Date dob;
        private String gender;
        private Integer cityId;
        private Integer districtId;
        private Integer wardId;
        private String street;
        private String phoneNumber;
        private String specialty;
        private Integer degreeYear;
        private Date startDate;
        private Integer departmentId;

        // Getters and Setters

        public String getUsername() {
            return username;
        }

    public void setDegreeYear(Integer degreeYear) {
        this.degreeYear = degreeYear;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getDegreeYear() {
        return degreeYear;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setUsername(String username) {
            this.username = username;
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


        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
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

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getSpecialty() {
            return specialty;
        }

        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }


        public Integer getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Integer departmentId) {
            this.departmentId = departmentId;
        }


}
