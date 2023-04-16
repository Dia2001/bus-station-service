package com.busstation.services;

import com.busstation.payload.request.CarRequest;
import com.busstation.payload.response.CarResponse;
import org.springframework.data.domain.Page;
import java.util.List;

public interface CarService {
    CarResponse updatedCar(String carId, CarRequest request);

    CarResponse addCar(CarRequest request);

    Boolean deleteCar(String id);

    List<CarResponse> getAllCar();

    Page<CarResponse> showAllCar(int pageNumber, int pageSize);

    CarResponse showCarNumber(int carNumber);
}
