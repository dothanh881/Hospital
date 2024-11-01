package com.hospital.hospitalmanagement.controller.admin;


import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private ExaminationMedicationRepository examinationMedicationRepository;



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

    @GetMapping("/examination/{id}")
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

    @GetMapping("/admission/{id}")
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


    @GetMapping("/edit/{id}")
    public String EditPatient(@PathVariable Integer id, Model model) {
        // Fetch the patient by ID
        Optional<PatientEntity> patient = patientRepository.findById(id);
        List<ExaminationEntity> examinations = examinationRepository.findByOutPatient_Patient_ID(id);
        // list doctor
        model.addAttribute("examinations", examinations);



        // Adding nested data for each examination's medications
        Map<Integer, List<ExaminationMedicationEntity>> examinationMedicationsMap = new HashMap<>();
        for (ExaminationEntity examination : examinations) {
            List<ExaminationMedicationEntity> medications = examinationMedicationRepository.findByExamination_ID(examination.getID());
            examinationMedicationsMap.put(examination.getID(), medications);
        }


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
