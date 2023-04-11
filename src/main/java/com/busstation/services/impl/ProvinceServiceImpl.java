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
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private static final String FILE_PATH = "Excel File/Provinces.xlsx";

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    CityRepository cityRepository;


    @Override
    public boolean createProvince(List<Province> provinces) {
        if (provinceRepository.saveAll(provinces) != null)
            return true;
        return false;
    }

    @Override
    public boolean createCity(List<City> cities) {
        if (cityRepository.saveAll(cities) != null)
            return true;
        return false;
    }

    @Override
    public List<ProvinceResponse> getAll() {

        List<Province> provinces = provinceRepository.findAll();

        List<ProvinceResponse> provinceResponseList = new ArrayList<>();
        for (Province province : provinces) {

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

        Province province = provinceRepository.findById(provinceId).orElseThrow(() -> new DataNotFoundException("Province not found"));

        City city = cityRepository.findById(provinceId).orElseThrow(() -> new DataNotFoundException("City not found"));

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

        if (province.isPresent()) {
            provinceRepository.delete(province.get());
            return true;
        }
        return false;
    }

    @Override
    public Boolean exportProvinces() {

        try (Workbook workbook = new XSSFWorkbook()) {
            File dataDir = new File("Excel File");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            
            
            
            List<Province> provinces = provinceRepository.findAll();
            Sheet sheet = workbook.createSheet("Provinces");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Province");
            header.createCell(1).setCellValue("Provinces Name");
            header.createCell(2).setCellValue("ID City");
            header.createCell(3).setCellValue("City Name");

            for (int i = 0; i < provinces.size(); i++) {
                Province province = provinces.get(i);
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(province.getProvinceId());
                row.createCell(1).setCellValue(province.getName());
                row.createCell(2).setCellValue(province.getCity().getCityId());
                row.createCell(3).setCellValue(province.getCity().getName());
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH)) {
                workbook.write(fileOutputStream);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProvinceResponse> importProvinces(MultipartFile file) throws IOException {
        List<ProvinceResponse> provinceResponses = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())){
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            if(rows.hasNext()){
                rows.next();
            }

            while(rows.hasNext()){
                Row row = rows.next();

                int idProvince = (int) row.getCell(0).getNumericCellValue();
                String provinceName = row.getCell(1).getStringCellValue();
                int idCity = (int) row.getCell(2).getNumericCellValue();
                String cityName = row.getCell(3).getStringCellValue();

                Province province = new Province(idProvince, provinceName, null);
                City city = new City(idCity, cityName,province);
                province.setCity(city);

                provinceRepository.save(province);
                cityRepository.save(city);

                CityResponse cityResponse = new CityResponse(idCity, cityName);
                ProvinceResponse provinceResponse = new ProvinceResponse(idProvince, provinceName, cityResponse);
                provinceResponses.add(provinceResponse);

            }

        }catch (IOException e){
            e.printStackTrace();
            throw e;
        }

        return provinceResponses;
    }


}
