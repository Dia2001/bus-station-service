package com.busstation.services;

import com.busstation.entities.Car;
import com.busstation.entities.Trip;
import com.busstation.payload.request.CarRequest;
import com.busstation.payload.response.CarResponse;
import org.springframework.stereotype.Service;

@Service
public interface CarService {
    public Car updatedCar(String carId, CarRequest request);
    CarResponse addCar(CarRequest request);
    public Car deleteCar(String id);
}
