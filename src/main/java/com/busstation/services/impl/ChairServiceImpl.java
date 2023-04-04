package com.busstation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.busstation.entities.Car;
import com.busstation.entities.Chair;
import com.busstation.payload.request.ChairRequest;
import com.busstation.payload.response.ChairResponse;
import com.busstation.repositories.CarRepository;
import com.busstation.repositories.ChairRepository;
import com.busstation.services.ChairService;

@Component
public class ChairServiceImpl implements ChairService {
	@Autowired
	private ChairRepository chairRepository;

	@Autowired
	private CarRepository carRepository;

	@Override
	public ChairResponse addChair(ChairRequest request) {
		Car car = carRepository.findById(request.getCarId())
				.orElseThrow(() -> new RuntimeException("Car ID does not exist"));
		Chair chair = new Chair();
		chair.setChairNumber(request.getChairNumber());
		chair.setCar(car);
		chairRepository.save(chair);

		Chair newChair = chairRepository.save(chair);
		ChairResponse chairResponse = new ChairResponse();
		chairResponse.setChairId(newChair.getChairId());
		chairResponse.setChairNumber(newChair.getChairNumber());
		chairResponse.setCarId(newChair.getCar().getCarId());

		return chairResponse;
	}

	@Override
	public boolean updateChair(String chairId, ChairRequest request) {
		Chair chair = chairRepository.findById(chairId)
				.orElseThrow(() -> new RuntimeException("Chair does not exist"));
		Car car = carRepository.findById(request.getCarId())
				.orElseThrow(() -> new RuntimeException("Car ID does not exist"));
		chair.setCar(car);
		chair.setChairNumber(request.getChairNumber());
		chairRepository.save(chair);
		
		return true;
	}

	@Override
	public boolean deleteChair(String chairId) {
		Chair chair = chairRepository.findById(chairId)
				.orElseThrow(()-> new RuntimeException("Id does not exist"));
		chairRepository.delete(chair);
		return true;
	}

}
