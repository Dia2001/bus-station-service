package com.busstation.payload.response;

import lombok.Data;

import java.util.Date;

@Data
public class TripResponse {

    private String tripId;

    private String provinceStart;

    private String provinceEnd;

    private Date timeStart;

}
