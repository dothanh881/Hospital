package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wards")
public class Wards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer WardId;

    private Integer DistrictId;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "IsDelete")
    private Boolean isDelete;

    private Integer OrderId;

    @Column(length = 255, nullable = false)
    private String WardCode;

    @Column(length = 255, nullable = false)
    private String WardName;

    // Getters and Setters
    public Integer getWardId() {
        return WardId;
    }

    public void setWardId(Integer wardId) {
        WardId = wardId;
    }

    public Integer getDistrictId() {
        return DistrictId;
    }

    public void setDistrictId(Integer districtId) {
        DistrictId = districtId;
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
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public String getWardCode() {
        return WardCode;
    }

    public void setWardCode(String wardCode) {
        WardCode = wardCode;
    }

    public String getWardName() {
        return WardName;
    }

    public void setWardName(String wardName) {
        WardName = wardName;
    }
}