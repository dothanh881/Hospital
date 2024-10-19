package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.Wards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardRepository extends JpaRepository<Wards,Integer> {
    public List<Wards> findByDistrict_DistrictIdOrderByOrderIdAsc(Integer districtId);
}