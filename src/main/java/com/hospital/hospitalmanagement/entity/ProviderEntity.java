package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "provider")
public class ProviderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "street", length = 255)
    private String street;

    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "CityId", insertable = false, updatable = false)
    private Cities city;

    @ManyToOne
    @JoinColumn(name = "districtId", referencedColumnName = "DistrictId", insertable = false, updatable = false)
    private Districts district;

    @ManyToOne
    @JoinColumn(name = "wardId", referencedColumnName = "WardId", insertable = false, updatable = false)
    private Wards ward;
    @Column(name = "phoneNumber", length = 15)
    private String phoneNumber;


    @OneToMany(mappedBy = "providerImport")
    private List<MedicationImportEntity> medicationImport;

    public ProviderEntity(String name, String street, Cities city, Districts district, Wards ward, String phoneNumber, List<MedicationImportEntity> medicationImport) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.phoneNumber = phoneNumber;
        this.medicationImport = medicationImport;
    }

    public ProviderEntity() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<MedicationImportEntity> getMedicationImport() {
        return medicationImport;
    }

    public void setMedicationImport(List<MedicationImportEntity> medicationImport) {
        this.medicationImport = medicationImport;
    }
}
