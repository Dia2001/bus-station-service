package com.busstation.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    private Boolean status;
    private int carNumber;
    private String tripId;
}
