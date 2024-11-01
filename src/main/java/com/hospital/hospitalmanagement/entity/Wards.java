package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wards")
public class Wards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wardId;



    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "IsDelete")
    private Boolean isDelete;

    private Integer orderId;

    @Column(length = 255, nullable = false)
    private String wardCode;

    @Column(length = 255, nullable = false)
    private String wardName;

    @ManyToOne
    @JoinColumn(name="districtId")
    private Districts district;

    // Getters and Setters
    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }


    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
