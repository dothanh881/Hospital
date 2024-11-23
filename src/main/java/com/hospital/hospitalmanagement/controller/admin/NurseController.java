package com.hospital.hospitalmanagement.controller.admin;

import com.hospital.hospitalmanagement.entity.*;
import com.hospital.hospitalmanagement.repository.*;
import com.hospital.hospitalmanagement.service.DepartmentService;
import com.hospital.hospitalmanagement.service.DoctorService;
import com.hospital.hospitalmanagement.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class NurseController extends BaseController {
    @Autowired
    private NurseService nurseService;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;

    @GetMapping("/nurses/{pageNo}")
    public String nursePage(@PathVariable("pageNo") int pageNo, Model model){
        Page<NurseEntity> nurses = nurseService.pageNurse(pageNo);
        List<Cities> cities = cityRepository.findAll();
        List<DepartmentEntity> departments = departmentService.findDepartments_ByActivePage();

        model.addAttribute("departments", departments);
        model.addAttribute("cities", cities);
        model.addAttribute("size", nurses.getSize());
        model.addAttribute("totalPages", nurses.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("nurses", nurses);

        return "admin/nurse";

    }
    @GetMapping("/nurse/edit/{id}")
    public String showNursePage(@PathVariable("id") Integer id, Model model) {
        Optional<NurseEntity> optionalNurse = nurseRepository.findById(id);

        if (optionalNurse.isEmpty()) {
            // Handle the case where the doctor does not exist
            model.addAttribute("errorMessage", "Nurse not found!");
            return "errorPage"; // Redirect to an error page or another appropriate response
        }

        NurseEntity nurse = optionalNurse.get(); // Safely unwrap the DoctorEntity

        // Fetch related data
        List<Cities> cities = cityRepository.findAll();
        List<Districts> districts = districtRepository.findByCity_CityIdOrderByOrderIdAsc(nurse.getCity().getCityId());
        List<Wards> wards = wardRepository.findByDistrict_DistrictIdOrderByOrderIdAsc(nurse.getDistrict().getDistrictId());
        List<DepartmentEntity> departments = departmentService.findDepartments_ByActivePage();

        // Add attributes to the model
        model.addAttribute("cities", cities);
        model.addAttribute("districts", districts);
        model.addAttribute("wards", wards);
        model.addAttribute("departments", departments);
        model.addAttribute("nurse", nurse);

        return "admin/nursedetail"; // Render the appropriate view
    }
    @GetMapping("/nurses-search/{pageNo}")
    public String searchDoctor(@PathVariable("pageNo") int pageNo, Model model,@RequestParam Map<String, Object> searchParams ){
        Page<NurseEntity> nurses = nurseService.searchNurse(searchParams,pageNo);
        List<Cities> cities = cityRepository.findAll();
        List<DepartmentEntity> departments = departmentService.findDepartments_ByActivePage();
        String queryString = searchParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        model.addAttribute("searchParams", searchParams);
        model.addAttribute("queryString", queryString);  // Add queryString to the model
        model.addAttribute("cities", cities);
        model.addAttribute("size", nurses.getSize());
        model.addAttribute("totalPages", nurses.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("nurses", nurses);
        model.addAttribute("departments", departments);

        return "admin/nurse-result";

    }


}
