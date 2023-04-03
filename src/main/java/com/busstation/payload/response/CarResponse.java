package com.busstation.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarResponse {
    private String carId;
    private Boolean status;
    private int carNumber;
    private String tripId;
}
