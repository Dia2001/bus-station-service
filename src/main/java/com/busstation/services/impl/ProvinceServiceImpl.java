package com.busstation.services.impl;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import com.busstation.exception.DataNotFoundException;
import com.busstation.payload.request.ProvinceRequest;
import com.busstation.payload.response.CityResponse;
import com.busstation.payload.response.ProvinceResponse;
import com.busstation.repositories.CityRepository;
import com.busstation.repositories.ProvinceRepository;
import com.busstation.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<ProvinceResponse> getAll() {

        List<Province> provinces = provinceRepository.findAll();

        List<ProvinceResponse> provinceResponseList = new ArrayList<>();
        for (Province province : provinces){

            ProvinceResponse provinceResponse = new ProvinceResponse();
            provinceResponse.setProvinceId(province.getProvinceId());
            provinceResponse.setName(province.getName());

            CityResponse cityResponse = new CityResponse();
            cityResponse.setCityId(province.getCity().getCityId());
            cityResponse.setName(province.getCity().getName());

            provinceResponse.setCity(cityResponse);

            provinceResponseList.add(provinceResponse);
        }

        return provinceResponseList;
    }

    @Override
    public ProvinceResponse createProvince(ProvinceRequest request) {

        Province province = new Province();
        province.setName(request.getNameProvince());

        Province newProvince = provinceRepository.save(province);

        City city = new City();
        city.setProvince(newProvince);
        city.setName(request.getNameCity());

        City newCity = cityRepository.save(city);

        ProvinceResponse response = new ProvinceResponse();
        response.setProvinceId(newProvince.getProvinceId());
        response.setName(newProvince.getName());

        CityResponse cityResponse = new CityResponse();
        cityResponse.setCityId(newCity.getCityId());
        cityResponse.setName(newCity.getName());

        response.setCity(cityResponse);

        return response;
    }

    @Override
    public ProvinceResponse updateProvince(ProvinceRequest request, int provinceId) {

        Province province = provinceRepository.findById(provinceId).orElseThrow(()->new DataNotFoundException("Province not found"));

        City city = cityRepository.findById(provinceId).orElseThrow(()->new DataNotFoundException("City not found"));

        province.setName(request.getNameProvince());
        province.getCity().setName(request.getNameCity());

        city.setName(request.getNameCity());
        cityRepository.save(city);

        Province updateProvince = provinceRepository.save(province);

        ProvinceResponse response = new ProvinceResponse();
        response.setProvinceId(updateProvince.getProvinceId());
        response.setName(updateProvince.getName());

        CityResponse cityResponse = new CityResponse();
        cityResponse.setCityId(updateProvince.getCity().getCityId());
        cityResponse.setName(updateProvince.getCity().getName());

        response.setCity(cityResponse);

        return response;
    }

    @Override
    public Boolean deleteProvince(int provinceId) {

        Optional<Province> province = provinceRepository.findById(provinceId);

        if(province.isPresent()){
            provinceRepository.delete(province.get());
            return true;
        }
        return false;
    }
}
