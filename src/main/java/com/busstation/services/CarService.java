package com.busstation.services;

import com.busstation.entities.Car;
import com.busstation.entities.Trip;
import com.busstation.payload.request.CarRequest;
import com.busstation.payload.response.CarResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    CarResponse updatedCar(String carId, CarRequest request);
    CarResponse addCar(CarRequest request);
    List<CarResponse> getAllCar();
    Boolean deleteCar(String id);

    Page<CarResponse> showAllCar(int pageNumber, int pageSize);
    Page<CarResponse> showCarNumber(int pageNumber, int pageSize, int carNumber);
}
