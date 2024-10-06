package com.hospital.hospitalmanagement.service;

import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.ExaminationEntity;
import com.hospital.hospitalmanagement.entity.OutPatientEntity;
import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import com.hospital.hospitalmanagement.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
    public class ExaminationService {

        @Autowired
        private ExaminationRepository examinationRepository;

//        public ExaminationEntity addNewExamination(ExaminationDTO dto, OutPatientEntity outPatient) {
//
//            ExaminationEntity examination = new ExaminationEntity();
//            examination.setOutPatient(outPatient);
//            examination.setExaminationDate(dto.getExaminationDate());
//            examination.setDiagnosis(dto.getDiagnosis());
//            examination.setFee(dto.getFee());
//
//            return examinationRepository.save(examination);
//        }
//
//        // Covert to DTO
//        public ExaminationDTO convertToExaminationDTO(ExaminationEntity examination) {
//            ExaminationDTO dto = new ExaminationDTO();
//            dto.setId(examination.getId());
//            dto.setExaminationDate(examination.getExaminationDate());
//            dto.setDiagnosis(examination.getDiagnosis());
//            dto.setFee(examination.getFee());
//            return dto;
//
//        }
    }
