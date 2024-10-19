package com.hospital.hospitalmanagement.repository;

import com.hospital.hospitalmanagement.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CityRepository extends JpaRepository<Cities,Integer> {
}
