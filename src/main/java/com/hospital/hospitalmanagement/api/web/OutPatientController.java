package com.hospital.hospitalmanagement.api.web;

import com.hospital.hospitalmanagement.entity.OutPatientEntity;
import com.hospital.hospitalmanagement.models.dto.ExaminationDTO;
import com.hospital.hospitalmanagement.models.dto.OutPatientDTO;
import com.hospital.hospitalmanagement.models.dto.OutPatientWithExaminationDTO;
import com.hospital.hospitalmanagement.service.ExaminationService;
import com.hospital.hospitalmanagement.service.OutPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/outpatients")
public class OutPatientController {

    @Autowired
    private OutPatientService outPatientService;

    @Autowired
    private ExaminationService examinationService;
//
//    @PostMapping
//    public ResponseEntity<OutPatientDTO> addNewOutPatientWithExamination(@RequestBody OutPatientWithExaminationDTO dto) {
//
//        OutPatientEntity newOutPatient = outPatientService.addNewOutPatient(dto);
//
//
//        ExaminationDTO examinationDTO = new ExaminationDTO();
//        examinationDTO.setExaminationDate(dto.getExaminationDate());
//        examinationDTO.setDiagnosis(dto.getDiagnosis());
//        examinationDTO.setFee(dto.getFee());
//
//        examinationService.addNewExamination(examinationDTO, newOutPatient);
//
//
//        OutPatientDTO responseDTO = outPatientService.convertToOutPatientDTO(newOutPatient);
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
//    }
}