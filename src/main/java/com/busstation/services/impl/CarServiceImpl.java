package com.busstation.services.impl;

import com.busstation.entities.Car;
import com.busstation.entities.Chair;
import com.busstation.entities.Trip;
import com.busstation.payload.request.CarRequest;
import com.busstation.payload.response.CarResponse;
import com.busstation.payload.response.ChairResponse;
import com.busstation.repositories.CarRepository;
import com.busstation.repositories.ChairRepository;
import com.busstation.repositories.TripRepository;
import com.busstation.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private ChairRepository chairRepository;

    @Override
    public CarResponse updatedCar(String carId, CarRequest request) {
        Car car = carRepository.findById(carId).orElseThrow(()-> new RuntimeException("Car does not exist"));
        Trip trip = tripRepository.findById(request.getTripId()).orElseThrow(()-> new RuntimeException("Trip does not exist"));

        List<Chair> chair = chairRepository.findAllByCar(car);


        List<ChairResponse> listChairResponse = new ArrayList<>();

        for(Chair item : chair){
            ChairResponse chairResponse = new ChairResponse();
            chairResponse.setStatus(item.getStatus());
            chairResponse.setCarId(item.getCar().getCarId());
            chairResponse.setChairNumber(item.getChairNumber());
            chairResponse.setChairId(item.getChairId());
            listChairResponse.add(chairResponse);
        }

        car.setStatus(request.getStatus());
        car.setCarNumber(request.getCarNumber());
        car.setTrips(trip);
        carRepository.save(car);

        CarResponse response = new CarResponse();
        response.setCarId(car.getCarId());
        response.setCarNumber(car.getCarNumber());
        response.setStatus(car.getStatus());
        response.setChair(listChairResponse);
        response.setTripId(trip.getTripId());

        return response;
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
    public List<CarResponse> getAllCar() {
        List<Car> cars  = carRepository.findAll();

        List<CarResponse> carResponses = new ArrayList<>();


        for(Car car : cars){
            CarResponse carResponse = new CarResponse();
            carResponse.setCarId(car.getCarId());
            carResponse.setCarNumber(car.getCarNumber());
            carResponse.setTripId(car.getTrips().getTripId());
            carResponse.setStatus(car.getStatus());
            List<ChairResponse> chairResponses = new ArrayList<>();
            for (Chair chair : car.getChairs()) {
                ChairResponse chairRes = new ChairResponse();
                chairRes.setChairId(chair.getChairId());
                chairRes.setChairNumber(chair.getChairNumber());
                chairRes.setCarId(chair.getCar().getCarId());
                chairRes.setStatus(chair.getStatus());

                chairResponses.add(chairRes);
            }

            carResponse.setChair(chairResponses);
            carResponses.add(carResponse);

        }

        return carResponses;
    }

    @Override
    public Boolean deleteCar(String id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("Id does not exist"));
        carRepository.delete(car);
        return true;
    }

    @Override
    public Page<CarResponse> showAllCar(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("carNumber").ascending());

        Page<Car> cars = carRepository.findAll(pageable);

        return cars.map(CarResponse::new);



    }

    @Override
    public Page<CarResponse> showCarNumber(int pageNumber, int pageSize, int carNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Car> cars = carRepository.findAllByCarNumber(carNumber, pageable);
        return cars.map(CarResponse::new);
    }
}
