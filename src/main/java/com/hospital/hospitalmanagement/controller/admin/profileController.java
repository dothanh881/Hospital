package com.hospital.hospitalmanagement.controller.admin;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.repository.CityRepository;
import com.hospital.hospitalmanagement.repository.DepartmentRepository;
import com.hospital.hospitalmanagement.repository.DistrictRepository;
import com.hospital.hospitalmanagement.repository.WardRepository;
import com.hospital.hospitalmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class profileController extends BaseController {
    @Autowired
   private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/profile/{id}")
    public String showProfilePage(@PathVariable("id") Integer id, Model model) {

        List<Cities> cities = cityRepository.findAll();
        model.addAttribute("cities", cities);
        List<Districts> districts = districtRepository.findByCity_CityIdOrderByOrderIdAsc(addEmployeeToModel().getCity().getCityId());
        List<Wards> wards = wardRepository.findByDistrict_DistrictIdOrderByOrderIdAsc(addEmployeeToModel().getDistrict().getDistrictId());
        List<DepartmentEntity> departments = departmentService.findDepartments_ByActivePage();


        model.addAttribute("departments", departments);

        model.addAttribute("districts", districts);
        model.addAttribute("wards", wards);
        return "admin/profile"; // This corresponds to login.html in your resources/templates folder
    }
}
