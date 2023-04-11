package com.busstation.payload.response;

import com.busstation.entities.Trip;
import com.busstation.repositories.TripRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {

    private String tripId;

    private String provinceStart;

    private String provinceEnd;

    private Date timeStart;

    private BigDecimal price;

    public TripResponse(Trip trip){

        this.tripId = trip.getTripId();
        this.provinceStart = trip.getProvinceStart();
        this.provinceEnd = trip.getProvinceEnd();
        this.timeStart = trip.getTimeStart();
    }
}
