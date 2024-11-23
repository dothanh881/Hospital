package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.NurseEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface NurseService {
    Page<NurseEntity> pageNurse(int pageNo);
    Page<NurseEntity> searchNurse(Map<String,Object> param, int pageNo);
}
