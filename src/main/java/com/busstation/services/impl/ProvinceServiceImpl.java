package com.busstation.services.impl;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import com.busstation.repositories.CityRepository;
import com.busstation.repositories.ProvinceRepository;
import com.busstation.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public boolean createProvince(List<Province> provinces) {
        if(provinceRepository.saveAll(provinces) != null)
            return true;
        return false;
    }

    @Override
    public boolean createCity(List<City> cities) {
        if(cityRepository.saveAll(cities) != null)
            return true;
        return false;
    }
}
