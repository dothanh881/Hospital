package com.hospital.hospitalmanagement.entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "patient") // tên bảng sẽ gen trong db
@Inheritance(strategy = InheritanceType.JOINED) // cho phép tạo bảng của đối tượng chuyên biệt hóa
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;


    public PatientEntity(String firstName, String lastName, Date dateOfBirth, String gender, String street, String phoneNumber, Cities city, Districts district, Wards ward) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.street = street;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.phoneNumber = phoneNumber;

    }

    @Column(name = "firstName", nullable = false, length = 100)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "dob")
    private Date dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "street", length = 255)
    private String street;

    @Column(name = "phoneNumber", length = 15)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private Cities city;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private Districts district;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Wards ward;


    public PatientEntity() {

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Districts getDistrict() {
        return district;
    }

    public void setDistrict(Districts district) {
        this.district = district;
    }

    public Wards getWard() {
        return ward;
    }

    public void setWard(Wards ward) {
        this.ward = ward;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}
