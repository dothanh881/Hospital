package com.hospital.hospitalmanagement.controller;


import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients")
    public String listPatients(Model model) {
        List<PatientEntity> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        return "patient/information";
    }
//    @GetMapping("/patients")
//    public String listPatients(Model model) {
//        List<PatientEntity> patients = patientRepository.findAll();
//        model.addAttribute("patients", patients);
//        return "admin/patient";
//    }

}
