package com.busstation.services;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import java.util.List;

public interface ProvinceService {
    boolean createProvince(List<Province> provinces);

    boolean createCity(List<City> cities);
}
