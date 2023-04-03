package com.busstation.services;


import com.busstation.entities.Trip;
import com.busstation.payload.request.TripRequest;
import com.busstation.payload.response.TripResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface TripService {
    TripResponse createTrip(TripRequest tripRequest) throws IOException;

    Trip updateTrip(String id, TripRequest newTripRequest);

    Boolean deleteTrip(String id);
}
