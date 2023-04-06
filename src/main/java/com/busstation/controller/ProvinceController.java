package com.busstation.controller;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import com.busstation.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9999/")
@RestController(value = "provinceAPIofWeb")
@RequestMapping(value = "/api/v1/provinces")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;
    @PostMapping(value = "/addProvince")
    public ResponseEntity<?> saveProvince(@RequestBody List<Province> provinceList){
        return new ResponseEntity<>(provinceService.createProvince(provinceList), HttpStatus.OK);
    }

    @PostMapping(value = "/addCity")
    public ResponseEntity<?> saves(@RequestBody List<City> cities){
        return new ResponseEntity<>(provinceService.createCity(cities), HttpStatus.OK);
    }
}
