package com.busstation.payload.response;

import com.busstation.entities.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {

    private String tripId;

    private String provinceStart;

    private String provinceEnd;

    private Date timeStart;
}
