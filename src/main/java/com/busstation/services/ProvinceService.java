package com.busstation.services;

import com.busstation.entities.City;
import com.busstation.entities.Province;
import com.busstation.payload.request.ProvinceRequest;
import com.busstation.payload.response.ProvinceResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProvinceService {
    boolean createProvince(List<Province> provinces);

    boolean createCity(List<City> cities);
    List<ProvinceResponse> getAll();

    ProvinceResponse createProvince(ProvinceRequest request);

    ProvinceResponse updateProvince(ProvinceRequest request, int provinceId);

    Boolean deleteProvince(int provinceId);

    Boolean exportProvinces();

    List<ProvinceResponse> importProvinces(MultipartFile file) throws IOException;

}
