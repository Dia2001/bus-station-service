package com.busstation.services.impl;

import com.busstation.entities.Car;
import com.busstation.entities.Trip;
import com.busstation.payload.request.CarRequest;
import com.busstation.payload.response.CarResponse;
import com.busstation.repositories.CarRepository;
import com.busstation.repositories.TripRepository;
import com.busstation.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private TripRepository tripRepository;

    @Override
    public Car updatedCar(String carId, CarRequest request) {
        Car car = carRepository.findById(carId).orElseThrow(()-> new RuntimeException("Car does not exist"));
        Trip trip = tripRepository.findById(request.getTripId()).orElseThrow(()-> new RuntimeException("Trip does not exist"));
        car.setStatus(request.getStatus());
        car.setCarNumber(request.getCarNumber());
        car.setTrips(trip);

        return carRepository.save(car);
    }



    @Override
    public CarResponse addCar(CarRequest request) {
        Trip trip = tripRepository.findById(request.getTripId()).orElseThrow(()-> new RuntimeException("Trip does not exist"));
        Car car = new Car();
        car.setStatus(request.getStatus());
        car.setCarNumber(request.getCarNumber());
        car.setTrips(trip);
        carRepository.save(car);


        Car newCar = carRepository.save(car);
        CarResponse carResponse = new CarResponse();
        carResponse.setCarId(newCar.getCarId());
        carResponse.setStatus(newCar.getStatus());
        carResponse.setCarNumber(newCar.getCarNumber());
        carResponse.setTripId(newCar.getTrips().getTripId());
        return carResponse;
    }

    @Override
    public Car deleteCar(String id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("Id does not exist"));
        carRepository.delete(car);
        return carRepository.save(car);
    }
}
