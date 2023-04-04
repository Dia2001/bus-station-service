package com.busstation.services;


import com.busstation.entities.Trip;
import com.busstation.payload.request.TripRequest;
import com.busstation.payload.response.TripResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

public interface TripService {
    TripResponse createTrip(TripRequest tripRequest);

    TripResponse updateTrip(String id, TripRequest newTripRequest);

    Boolean deleteTrip(String id);
}
