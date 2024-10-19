package com.hospital.hospitalmanagement.controller.admin;


import com.hospital.hospitalmanagement.entity.Cities;
import com.hospital.hospitalmanagement.entity.Districts;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.entity.Wards;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.CityRepository;
import com.hospital.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CityRepository cityRepository;
//    @GetMapping("/patients")
//    public String listPatients(Model model) {
//        List<PatientEntity> patients = patientRepository.findAll();
//        model.addAttribute("patients", patients);
//        return "patient/information";
//    }
    @GetMapping("/patients")
    public String listPatients(Model model) {
        List<PatientEntity> patients = patientRepository.findAll();
        List<Cities> cities = cityRepository.findAll();
        model.addAttribute("patients", patients);
        model.addAttribute("cities",cities);
        return "admin/patient";
    }
//    @PostMapping("/patient/add")
//    public String addPatient(@ModelAttribute PatientDTO patientDTO, RedirectAttributes redirectAttributes) {
//        // Create a new PatientEntity object
//        PatientEntity patientEntity = new PatientEntity();
//
//        patientEntity.setFirstName(patientDTO.getFirstName());
//        patientEntity.setLastName(patientDTO.getLastName());
//        patientEntity.setGender(patientDTO.getGender());
//        patientEntity.setDateOfBirth(patientDTO.getDob());
//        patientEntity.setStreet(patientDTO.getStreet());
//        patientEntity.setPhoneNumber(patientDTO.getPhoneNumber());
//
//        // Set city, district, and ward
//        Cities city = new Cities();
//        city.setCityId(patientDTO.getCityId());
//        patientEntity.setCity(city);
//
//        Districts district = new Districts();
//        district.setDistrictId(patientDTO.getDistrictId());
//        patientEntity.setDistrict(district);
//
//        Wards ward = new Wards();
//        ward.setWardId(patientDTO.getWardId());
//        patientEntity.setWard(ward);
//
//        // Save the patient entity
//        patientRepository.save(patientEntity);
//
//        redirectAttributes.addFlashAttribute("message", "Patient added successfully!");
//
//        // Redirect to the patients list page
//        return "redirect:/patients";
//    }

}
