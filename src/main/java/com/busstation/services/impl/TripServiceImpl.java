package com.busstation.services.impl;

import com.busstation.entities.Trip;
import com.busstation.payload.request.TripRequest;
import com.busstation.payload.response.TripResponse;
import com.busstation.repositories.TripRepository;
import com.busstation.services.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public TripResponse createTrip(TripRequest tripRequest) throws IOException {

        /*
        * convert object tripRequest to an array byte.  byte[] bytes = ...
        * object ByteArrayInputStream to read the bytes of the object tripRequest
        * convert it to a DataInput object using DataInputStream.
        * parse request body using custom date format. TripRequest parsedRequest = ...
        * */

        byte[] bytes = objectMapper.writeValueAsBytes(tripRequest);
        ByteArrayInputStream basis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(basis);
        TripRequest parsedRequest = objectMapper.readValue((DataInput) dis, TripRequest.class);

        Trip trip = new Trip();
        trip.setProvinceStart(parsedRequest.getProvinceStart());
        trip.setProvinceEnd(parsedRequest.getProvinceEnd());
        trip.setTimeStart(parsedRequest.getTimeStart());

        Trip newTrip = tripRepository.save(trip);

        TripResponse tripResponse = new TripResponse();
        tripResponse.setTripId(newTrip.getTripId());
        tripResponse.setProvinceStart(newTrip.getProvinceStart());
        tripResponse.setProvinceEnd(newTrip.getProvinceEnd());
        tripResponse.setTimeStart(newTrip.getTimeStart());

        return tripResponse;
    }

    @Override
    public Trip updateTrip(String id, TripRequest newTripRequest) {

        Trip trip = tripRepository.findById(id).orElseThrow(()-> new RuntimeException("Trip does not exist"));

        trip.setProvinceStart(newTripRequest.getProvinceStart());
        trip.setProvinceEnd(newTripRequest.getProvinceEnd());
        trip.setTimeStart(newTripRequest.getTimeStart());
        trip.setUpdateAt(new Date());

        return tripRepository.save(trip);
    }

    @Override
    public Boolean deleteTrip(String id) {

        Trip trip = tripRepository.findById(id).orElseThrow(()-> new RuntimeException("Trip does not exist"));
        tripRepository.delete(trip);
        return true;
    }
}
