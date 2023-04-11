package com.busstation.payload.response;

import lombok.Data;

@Data
public class ProvinceResponse {

    private int provinceId;

    private String name;

    private CityResponse city;
}
