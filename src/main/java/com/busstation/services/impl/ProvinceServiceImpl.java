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
    public boolean createProvice(List<Province> provinces) {
        provinceRepository.saveAll(provinces);
        return true;
    }

    @Override
    public boolean createCiy(List<City> cities) {
        cityRepository.saveAll(cities);
        return false;
    }
}
