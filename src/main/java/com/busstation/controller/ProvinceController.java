package com.busstation.controller;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import com.busstation.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/provinces")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;
    @PostMapping(value = "/addProvince")
    public ResponseEntity<?> saveProvince(@RequestBody List<Province> provinceList){
        return new ResponseEntity<>(provinceService.createProvice(provinceList), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> saves(@RequestBody List<City> cities){
        return new ResponseEntity<>(provinceService.createCiy(cities), HttpStatus.OK);
    }
}
