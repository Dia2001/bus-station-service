package com.busstation.services;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvinceService {
   boolean  createProvice(List<Province> provinces);
   boolean  createCiy(List<City> cities);
}
