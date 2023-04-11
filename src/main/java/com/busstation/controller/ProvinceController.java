package com.busstation.controller;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import com.busstation.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9999/")
@RestController(value = "provinceAPIofWeb")
@RequestMapping(value = "/api/v1/provinces")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;
    @PostMapping(value = "/addListProvince")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_ADMIN')")
    public ResponseEntity<?> saveProvince(@RequestBody List<Province> provinceList){
        return new ResponseEntity<>(provinceService.createProvince(provinceList), HttpStatus.OK);
    }

    @PostMapping(value = "/addListCity")
    public ResponseEntity<?> saves(@RequestBody List<City> cities){
        return new ResponseEntity<>(provinceService.createCity(cities), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(provinceService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProvinceRequest request){
        return new ResponseEntity<>(provinceService.createProvince(request), HttpStatus.CREATED);
    }

    @PutMapping("/{province_id}")
    public ResponseEntity<?> update(@RequestBody ProvinceRequest request, @PathVariable("province_id") int provinceId){
        return new ResponseEntity<>(provinceService.updateProvince(request,provinceId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{province_id}")
    public ResponseEntity<?> delete(@PathVariable("province_id") int provinceId){
        Boolean deleted = provinceService.deleteProvince(provinceId);
        return new ResponseEntity<>(deleted,HttpStatus.OK);
    }
}
