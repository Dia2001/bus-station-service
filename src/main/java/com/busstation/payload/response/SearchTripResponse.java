package com.busstation.payload.response;

import com.busstation.entities.Car;
import com.busstation.entities.Chair;
import com.busstation.entities.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTripResponse {

    private String tripId;

    private String provinceStart;

    private String provinceEnd;

    private Date timeStart;

    private List<CarResponse> car;

    public SearchTripResponse(Trip trip){

        this.tripId = trip.getTripId();
        this.provinceStart = trip.getProvinceStart();
        this.provinceEnd = trip.getProvinceEnd();
        this.timeStart = trip.getTimeStart();

        List<CarResponse> carResponseList = new ArrayList<>();

        for(Car item : trip.getCars()){

            List<ChairResponse> chairResponseList = new ArrayList<>();

            for (Chair chair : item.getChairs()){
                ChairResponse chairResponse = new ChairResponse();
                chairResponse.setCarId(chair.getCar().getCarId());
                chairResponse.setChairId(chair.getChairId());
                chairResponse.setChairNumber(chair.getChairNumber());

                chairResponseList.add(chairResponse);
            }

            CarResponse carResponse = new CarResponse();
            carResponse.setCarId(item.getCarId());
            carResponse.setCarNumber(item.getCarNumber());
            carResponse.setChair(chairResponseList);
            carResponse.setStatus(item.getStatus());
            carResponse.setTripId(item.getTrips().getTripId());

            carResponseList.add(carResponse);
        }

        this.car = carResponseList;
    }
}
