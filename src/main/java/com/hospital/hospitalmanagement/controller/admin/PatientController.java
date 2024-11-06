package com.hospital.hospitalmanagement.controller.admin;


import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller

public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private TreatmentRepository treatmentRepository;




    // lấy danh sách patient
    @GetMapping("/patients")
    public String listPatients(Model model) {
        List<PatientEntity> patients = patientRepository.findAll();
        List<Cities> cities = cityRepository.findAll();
        model.addAttribute("patients", patients);
        model.addAttribute("cities",cities);
        return "admin/patient"; // tra ve page patient.html
    }

    @GetMapping("patient/examination/{id}")
    public String ResgisterExamination(@PathVariable Integer id, Model model) {
        // Fetch the patient by ID
        Optional<PatientEntity> patient = patientRepository.findById(id);
        List<DoctorEntity> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        List<MedicationEntity> medications = medicationRepository.findAll();
        model.addAttribute("medications", medications);
        if (patient.isPresent()) {
            // Add the patient to the model if found
            model.addAttribute("patient", patient.get());
        } else {
            // Handle the case where the patient is not found (optional)
            model.addAttribute("error", "Patient not found");
            return "errorPage";  // or some other view that shows an error
        }

        return "admin/registerExamination";
    }

    @GetMapping("patient/admission/{id}")
    public String ResgisterAdmission(@PathVariable Integer id, Model model) {
        // Fetch the patient by ID
        Optional<PatientEntity> patient = patientRepository.findById(id);
        // list doctor
        List<DoctorEntity> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        // list nurse
        List<NurseEntity> nurses = nurseRepository.findAll();
        model.addAttribute("nurses", nurses);

        List<RoomEntity> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);


        List<MedicationEntity> medications = medicationRepository.findAll();
        model.addAttribute("medications", medications);
        if (patient.isPresent()) {
            // Add the patient to the model if found
            model.addAttribute("patient", patient.get());
        } else {
            // Handle the case where the patient is not found (optional)
            model.addAttribute("error", "Patient not found");
            return "errorPage";  // or some other view that shows an error
        }

        return "admin/Admission";
    }


    @GetMapping("patient/edit/{id}")
    public String EditPatient(@PathVariable Integer id, Model model) {
        // Fetch the patient by ID
        Optional<PatientEntity> patient = patientRepository.findById(id);
        List<ExaminationEntity> examinations = examinationRepository.findByOutPatient_Patient_ID(id);

        List<AdmissionEntity> admissions = admissionRepository.findByInPatient_Patient_ID(id);

        // Prepare map to hold medications per examination

        model.addAttribute("examinations", examinations);
        model.addAttribute("admissions", admissions);
// Add the map to the model for use in the frontend



        List<DoctorEntity> doctors = doctorRepository.findAll();
         model.addAttribute("doctors", doctors);
        // list nurse
        List<NurseEntity> nurses = nurseRepository.findAll();
        model.addAttribute("nurses", nurses);

        List<RoomEntity> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);


        List<MedicationEntity> medications = medicationRepository.findAll();
        model.addAttribute("medications", medications);
        if (patient.isPresent()) {
            // Add the patient to the model if found
            model.addAttribute("patient", patient.get());
        } else {
            // Handle the case where the patient is not found (optional)
            model.addAttribute("error", "Patient not found");
            return "errorPage";  // or some other view that shows an error
        }

        return "admin/detailpatient";
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
