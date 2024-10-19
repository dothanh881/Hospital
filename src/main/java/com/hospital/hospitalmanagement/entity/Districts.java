package com.hospital.hospitalmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "districts")
public class Districts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer districtId;



    @Column(length = 10, nullable = false)
    private String DistrictCode;

    @Column(length = 100, nullable = false)
    private String DistrictName;

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
        districtId = districtId;
    }


    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
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
        orderId = orderId;
    }

    public Integer getDanSo() {
        return DanSo;
    }

    public void setDanSo(Integer danSo) {
        DanSo = danSo;
    }
}
