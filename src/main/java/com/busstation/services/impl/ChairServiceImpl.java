package com.busstation.services.impl;

import com.busstation.entities.Car;
import com.busstation.entities.Chair;
import com.busstation.payload.response.CarResponse;
import com.busstation.payload.response.ChairResponse;
import com.busstation.repositories.CarRepository;
import com.busstation.repositories.ChairRepository;
import com.busstation.services.ChairService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ChairServiceImpl implements ChairService {

    @Autowired
    private ChairRepository chairRepository;

    @Autowired
    private CarRepository carRepository;
    @Override
    public Page<ChairResponse> showAllChair(String carId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("chairNumber").ascending());

        Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Car does not exist"));

        Page<Chair> chairs = chairRepository.findAllByCar(car, pageable);


        return chairs.map(ChairResponse::new);
    }

    @Override
    public ChairResponse searchChairNumber(String carId, int chairNumber) {

        Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Car does not exist"));
        Chair chairs = chairRepository.findAllByCarAndChairNumber(car, chairNumber);

        ChairResponse chairResponse = new ChairResponse();
        chairResponse.setChairId(chairs.getChairId());
        chairResponse.setCarId(chairs.getCar().getCarId());
        chairResponse.setChairNumber(chairs.getChairNumber());
        chairResponse.setStatus(chairs.getStatus());
        return chairResponse;
    }
}
