package com.hospital.hospitalmanagement.controller.admin;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.repository.*;
import com.hospital.hospitalmanagement.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdmissionController extends BaseController{
    @Autowired
    AdmissionService admissionService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private TreatmentStatusRepository treatmentStatusRepository;
    @GetMapping("/admissions/{pageNo}")
    public String AdmissionPage(@PathVariable("pageNo") int pageNo, Model model){
        Page<AdmissionEntity> admissions = admissionService.pageAdmissions(pageNo);

        // Prepare map to hold medications per examination
        for (AdmissionEntity admission : admissions) {
            // Filter out inactive or deleted treatments
            admission.setTreatments(admission.getTreatments().stream()
                    .filter(treatment -> treatment.getActive() == true && treatment.getDeleted() == false)
                    .collect(Collectors.toList()));
        }
        List<TreatmentStatusEntity> treatmentStatus = treatmentStatusRepository.findAll();
        model.addAttribute("treatmentStatus", treatmentStatus);
        List<DoctorEntity> doctors = doctorRepository.getAllDoctor();
        model.addAttribute("doctors", doctors);
        // list nurse
        List<NurseEntity> nurses = nurseRepository.getAllNurse();
        model.addAttribute("nurses", nurses);

        List<RoomEntity> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);


        List<MedicationEntity> medications = medicationRepository.getAllMedication();
        model.addAttribute("medications", medications);

        model.addAttribute("size", admissions.getSize());
        model.addAttribute("totalPages", admissions.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("admissions", admissions);

        return "admin/listAdmission";

    }
    @GetMapping("/admissions-search/{pageNo}")
    public String searchAdmission(@PathVariable("pageNo") int pageNo, Model model,@RequestParam Map<String, Object> searchParams ){
        Page<AdmissionEntity> admissions = admissionService.searchAdmission(searchParams,pageNo);
        if (admissions == null) {
            // Handle null case (optional)
            admissions = Page.empty(); // or create an empty Page object to prevent null access
        }
        String queryString = searchParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));


        // Prepare map to hold medications per examination
        for (AdmissionEntity admission : admissions) {
            // Filter out inactive or deleted treatments
            admission.setTreatments(admission.getTreatments().stream()
                    .filter(treatment -> treatment.getActive() == true && treatment.getDeleted() == false)
                    .collect(Collectors.toList()));
        }

        List<TreatmentStatusEntity> treatmentStatus = treatmentStatusRepository.findAll();
        model.addAttribute("treatmentStatus", treatmentStatus);
        List<DoctorEntity> doctors = doctorRepository.getAllDoctor();
        model.addAttribute("doctors", doctors);
        // list nurse
        List<NurseEntity> nurses = nurseRepository.getAllNurse();
        model.addAttribute("nurses", nurses);

        List<RoomEntity> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);


        List<MedicationEntity> medications = medicationRepository.getAllMedication();
        model.addAttribute("medications", medications);



        model.addAttribute("searchParams", searchParams);
        model.addAttribute("queryString", queryString);  // Add queryString to the model
        model.addAttribute("size", admissions.getSize());
        model.addAttribute("totalPages", admissions.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("admissions", admissions);
        return "admin/admission-result";

    }
}
