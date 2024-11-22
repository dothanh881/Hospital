package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface  DoctorService {
    Page<DoctorEntity> pageDoctor(int pageNo);

}
