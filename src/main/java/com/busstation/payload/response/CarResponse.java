package com.busstation.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class CarResponse {

    private String carId;

    private Boolean status;

    private int carNumber;

    private List<ChairResponse> chair;

    private String tripId;
}
