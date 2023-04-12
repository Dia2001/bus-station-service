package com.busstation.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceResponse {

    private int provinceId;

    private String name;

    private CityResponse city;
}
