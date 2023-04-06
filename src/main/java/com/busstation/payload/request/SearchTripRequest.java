package com.busstation.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class SearchTripRequest {

    String provinceStart;

    String provinceEnd;

    Date dateTime;
}
