package com.busstation.payload.response;

import com.busstation.entities.Car;
import com.busstation.entities.Chair;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResponse {
    private String carId;
    private Boolean status;
    private int carNumber;
    private List<ChairResponse> chair;
    private String tripId;

    public CarResponse(Car car){
        this.carId = car.getCarId();
        this.tripId = car.getTrips().getTripId();
        this.status = car.getStatus();
        this.carNumber = car.getCarNumber();


        List<ChairResponse> chairResponseList = new ArrayList<>();

        for (Chair chair : car.getChairs()){
            ChairResponse chairRes = new ChairResponse();
            chairRes.setChairId(chair.getChairId());
            chairRes.setCarId(chair.getCar().getCarId());
            chairRes.setChairNumber(chair.getChairNumber());
            chairRes.setStatus(chair.getStatus());

            chairResponseList.add(chairRes);
        }
        this.chair = chairResponseList;
    }
}