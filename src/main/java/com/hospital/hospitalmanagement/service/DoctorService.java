package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface  DoctorService {
    Page<DoctorEntity> pageDoctor(int pageNo);
    Page<DoctorEntity> searchDoctor(Map<String,Object> param, int pageNo);


}
