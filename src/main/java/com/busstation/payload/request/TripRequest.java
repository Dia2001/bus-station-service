package com.busstation.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class TripRequest {

    private String provinceStart;

    private String provinceEnd;

    private Date timeStart;

    private Date updateAt;

}
