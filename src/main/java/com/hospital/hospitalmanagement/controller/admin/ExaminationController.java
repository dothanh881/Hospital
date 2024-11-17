package com.hospital.hospitalmanagement.controller.admin;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.repository.DoctorRepository;
import com.hospital.hospitalmanagement.repository.ExaminationRepository;
import com.hospital.hospitalmanagement.repository.MedicationRepository;
import com.hospital.hospitalmanagement.service.ExaminationService;
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
public class ExaminationController {

    @Autowired
    ExaminationService examinationService;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    MedicationRepository medicationRepository;
    @GetMapping("/examinations/{pageNo}")
    public String ExaminationPage(@PathVariable("pageNo") int pageNo, Model model){
        Page<ExaminationEntity> examinations = examinationService.pageExaminations(pageNo);

        // Prepare map to hold medications per examination


        List<DoctorEntity> doctors = doctorRepository.getAllDoctor();
        model.addAttribute("doctors", doctors);





        List<MedicationEntity> medications = medicationRepository.getAllMedication();
        model.addAttribute("medications", medications);

        model.addAttribute("size", examinations.getSize());
        model.addAttribute("totalPages", examinations.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("examinations", examinations);

        return "admin/listExamination";

    }
    @GetMapping("/examinations-search/{pageNo}")
    public String searchExamination(@PathVariable("pageNo") int pageNo, Model model,@RequestParam Map<String, Object> searchParams ){
        Page<ExaminationEntity> examinations = examinationService.searchExamination(searchParams,pageNo);
        String queryString = searchParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));




        List<DoctorEntity> doctors = doctorRepository.getAllDoctor();
        model.addAttribute("doctors", doctors);
        // list nurse



        List<MedicationEntity> medications = medicationRepository.getAllMedication();
        model.addAttribute("medications", medications);



        model.addAttribute("searchParams", searchParams);
        model.addAttribute("queryString", queryString);  // Add queryString to the model
        model.addAttribute("size", examinations.getSize());
        model.addAttribute("totalPages", examinations.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("examinations", examinations);
        return "admin/examination-result";

    }
}
