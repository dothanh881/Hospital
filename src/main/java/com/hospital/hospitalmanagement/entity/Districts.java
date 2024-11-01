package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "districts")
public class Districts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer districtId;



    @Column(length = 10, nullable = false)
    private String districtCode;

    @Column(length = 100, nullable = false)
    private String districtName;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "IsDelete")
    private Boolean isDelete;


    @ManyToOne
    @JoinColumn(name="city_id")
    private Cities city;


    private Integer orderId;

    private Integer DanSo;

    // Getters and Setters
    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }


    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public Integer getDanSo() {
        return DanSo;
    }

    public void setDanSo(Integer danSo) {
        this.DanSo = danSo;
    }
}
