package com.busstation.controller;

import com.busstation.entities.Car;
import com.busstation.payload.request.CarRequest;
import com.busstation.payload.response.CarResponse;
import com.busstation.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody CarRequest request){
        CarResponse carResponse = carService.addCar(request);
        return new ResponseEntity<>(carResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@RequestBody CarRequest request, @PathVariable("id") String carId){
        Car car = carService.updatedCar(carId, request);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") String id){
        Car car = carService.deleteCar(id);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

}
