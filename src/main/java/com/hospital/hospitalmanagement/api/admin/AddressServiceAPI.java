package com.hospital.hospitalmanagement.api.admin;

import com.hospital.hospitalmanagement.entity.Cities;
import com.hospital.hospitalmanagement.entity.Districts;
import com.hospital.hospitalmanagement.entity.Wards;
import com.hospital.hospitalmanagement.repository.CityRepository;
import com.hospital.hospitalmanagement.repository.DistrictRepository;
import com.hospital.hospitalmanagement.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressServiceAPI {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;

    @GetMapping("/cities")
    public ResponseEntity<List<Cities>> getCities(){
        List<Cities> cities = cityRepository.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


    @GetMapping("/districts/{cityId}")
    public ResponseEntity<List<Districts>> getDistricts(@PathVariable Integer cityId){
        List<Districts> districts = districtRepository.findByCity_CityIdOrderByOrderIdAsc(cityId);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/wards/{districtId}")
    public ResponseEntity<List<Wards>> getWards(@PathVariable Integer districtId){
        List<Wards> wards = wardRepository.findByDistrict_DistrictIdOrderByOrderIdAsc(districtId);
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }

}
