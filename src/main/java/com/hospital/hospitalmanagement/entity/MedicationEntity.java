package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medication")
public class MedicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "effect", length = 255)
    private String effect;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    @OneToMany(mappedBy = "medication")
    private List<ExaminationMedicationEntity> examinationMedications = new ArrayList<>();

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @OneToMany(mappedBy = "medicationImport")
    private List<MedicationImportEntity> medicationImport;


    @OneToMany(mappedBy = "medication")
    private List<TreatmentMedicationEntity> treatmentMedications;

    public MedicationEntity(String code, String name, String effect, BigDecimal price, Date expirationDate, List<ExaminationMedicationEntity> examinationMedications, List<MedicationImportEntity> medicationImport, List<TreatmentMedicationEntity> treatmentMedications) {
        this.code = code;
        this.name = name;
        this.effect = effect;
        this.price = price;
        this.expirationDate = expirationDate;
        this.examinationMedications = examinationMedications;
        this.medicationImport = medicationImport;
        this.treatmentMedications = treatmentMedications;
    }

    public MedicationEntity() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }



    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<ExaminationMedicationEntity> getExaminationMedications() {
        return examinationMedications;
    }

    public void setExaminationMedications(List<ExaminationMedicationEntity> examinationMedications) {
        this.examinationMedications = examinationMedications;
    }

    public List<MedicationImportEntity> getMedicationImport() {
        return medicationImport;
    }

    public void setMedicationImport(List<MedicationImportEntity> medicationImport) {
        this.medicationImport = medicationImport;
    }

    public List<TreatmentMedicationEntity> getTreatmentMedications() {
        return treatmentMedications;
    }

    public void setTreatmentMedications(List<TreatmentMedicationEntity> treatmentMedications) {
        this.treatmentMedications = treatmentMedications;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
