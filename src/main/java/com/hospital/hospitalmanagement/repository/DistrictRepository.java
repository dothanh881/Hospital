package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.Districts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<Districts,Integer> {

    List<Districts> findByCity_CityIdOrderByOrderIdAsc(Integer cityId);
}
