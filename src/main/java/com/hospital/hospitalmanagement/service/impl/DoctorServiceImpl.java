package com.hospital.hospitalmanagement.service.impl;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.repository.DoctorRepository;
import com.hospital.hospitalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public Page<DoctorEntity> pageDoctor(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 10);
        Page<DoctorEntity> pageDoctor = doctorRepository.findDoctor_ByActivePage(pageable);
        return pageDoctor;
    }
}
