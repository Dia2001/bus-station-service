package com.busstation.services;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvinceService {
   boolean  createProvince(List<Province> provinces);
   boolean  createCity(List<City> cities);
}
