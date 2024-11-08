package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.JOINED) // Specifies the inheritance strategy
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob;  // Date of birth

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String street;


    @Column( nullable = false)
    private String specialty;

    @Column( nullable = false)
    private int degreeYear;


    @ManyToOne
    @JoinColumn(name = "city_id")
    private Cities city;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private Districts district;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Wards ward;

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getDegreeYear() {
        return degreeYear;
    }

    public void setDegreeYear(int degreeYear) {
        this.degreeYear = degreeYear;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    // No-arg constructor
    public EmployeeEntity() {
    }

    public EmployeeEntity(String code, String firstName, String lastName, Date dob, String gender, String phoneNumber, String street, String specialty, int degreeYear, Cities city, Districts district, Wards ward, Date startDate, DepartmentEntity department) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.specialty = specialty;
        this.degreeYear = degreeYear;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.startDate = startDate;
        this.department = department;
    }


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
