package com.busstation.payload.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TripRequest {

    private String provinceStart;

    private String provinceEnd;

    private Date timeStart;

    private Date updateAt;

    private BigDecimal price;

}
