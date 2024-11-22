package com.hospital.hospitalmanagement.controller.admin;

import com.hospital.hospitalmanagement.entity.Cities;
import com.hospital.hospitalmanagement.entity.DepartmentEntity;
import com.hospital.hospitalmanagement.entity.DoctorEntity;
import com.hospital.hospitalmanagement.entity.PatientEntity;
import com.hospital.hospitalmanagement.repository.CityRepository;
import com.hospital.hospitalmanagement.service.DepartmentService;
import com.hospital.hospitalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DoctorController extends BaseController{

        @Autowired
        private DoctorService doctorService;
        @Autowired
        private CityRepository cityRepository;
        @Autowired
    private DepartmentService departmentService;

    @GetMapping("/doctors/{pageNo}")
    public String doctorPage(@PathVariable("pageNo") int pageNo, Model model){
        Page<DoctorEntity> doctors = doctorService.pageDoctor(pageNo);
        List<Cities> cities = cityRepository.findAll();
        List<DepartmentEntity> departments = departmentService.findDepartments_ByActivePage();


        model.addAttribute("departments", departments);
        model.addAttribute("cities", cities);
        model.addAttribute("size", doctors.getSize());
        model.addAttribute("totalPages", doctors.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("doctors", doctors);

        return "admin/doctor";

    }


}
