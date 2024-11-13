package com.hospital.hospitalmanagement.controller.admin;


import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.models.dto.PatientDTO;
import com.hospital.hospitalmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;
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

    @Autowired
    private TreatmentStatusRepository treatmentStatusRepository;


    // lấy danh sách patient
    @GetMapping("/patients/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        // Default page size is 10
        Pageable pageable = (Pageable) PageRequest.of(p.orElse(0), 10);  // Correct Pageable class

        // Get active patients with pagination
        Page<PatientEntity> patients = patientRepository.findPatient_ByActive(pageable);

        // Get all cities for the dropdown
        List<Cities> cities = cityRepository.findAll();

        // Add patients and cities to the model
        model.addAttribute("patients", patients);
        model.addAttribute("cities", cities);

        // Return the page template
        return "admin/patient";  // Adjust this to your view template
    }

    @GetMapping("patient/examination/{id}")
    public String ResgisterExamination(@PathVariable Integer id, Model model) {
        // Fetch the patient by ID
        Optional<PatientEntity> patient = patientRepository.findPatient_Id(id);
        List<DoctorEntity> doctors = doctorRepository.getAllDoctor();
        model.addAttribute("doctors", doctors);
        List<MedicationEntity> medications = medicationRepository.getAllMedication();
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
        Optional<PatientEntity> patient = patientRepository.findPatient_Id(id);
        // list doctor
        List<DoctorEntity> doctors = doctorRepository.getAllDoctor();
        model.addAttribute("doctors", doctors);
        // list nurse
        List<NurseEntity> nurses = nurseRepository.getAllNurse();
        model.addAttribute("nurses", nurses);

        List<RoomEntity> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);


        List<MedicationEntity> medications = medicationRepository.getAllMedication();
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
        Optional<PatientEntity> patient = patientRepository.findPatient_Id(id);
        List<ExaminationEntity> examinations = examinationRepository.getAllExaminationByPatient(id);

        List<AdmissionEntity> admissions = admissionRepository.getAllAdmissionByPatient(id);
        List<TreatmentStatusEntity> treatmentStatus = treatmentStatusRepository.findAll();



        // Prepare map to hold medications per examination
        for (AdmissionEntity admission : admissions) {
            // Filter out inactive or deleted treatments
            admission.setTreatments(admission.getTreatments().stream()
                    .filter(treatment -> treatment.getActive() == true && treatment.getDeleted() == false)
                    .collect(Collectors.toList()));
        }

        List<Cities> cities = cityRepository.findAll();
        List<Districts> districts = districtRepository.findByCity_CityIdOrderByOrderIdAsc(patient.get().getCity().getCityId());
        List<Wards> wards = wardRepository.findByDistrict_DistrictIdOrderByOrderIdAsc(patient.get().getDistrict().getDistrictId());


        model.addAttribute("cities", cities);
        model.addAttribute("districts", districts);
        model.addAttribute("wards", wards);




        model.addAttribute("examinations", examinations);
        model.addAttribute("admissions", admissions);
        model.addAttribute("treatmentStatus", treatmentStatus);
// Add the map to the model for use in the frontend



        List<DoctorEntity> doctors = doctorRepository.getAllDoctor();
         model.addAttribute("doctors", doctors);
        // list nurse
        List<NurseEntity> nurses = nurseRepository.getAllNurse();
        model.addAttribute("nurses", nurses);

        List<RoomEntity> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);


        List<MedicationEntity> medications = medicationRepository.getAllMedication();
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
