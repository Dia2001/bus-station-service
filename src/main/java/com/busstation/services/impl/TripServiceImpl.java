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


    @Override
    public TripResponse createTrip(TripRequest tripRequest){

        Trip trip = new Trip();
        trip.setProvinceStart(tripRequest.getProvinceStart());
        trip.setProvinceEnd(tripRequest.getProvinceEnd());
        trip.setTimeStart(tripRequest.getTimeStart());

        Trip newTrip = tripRepository.save(trip);

        TripResponse tripResponse = new TripResponse();
        tripResponse.setTripId(newTrip.getTripId());
        tripResponse.setProvinceStart(newTrip.getProvinceStart());
        tripResponse.setProvinceEnd(newTrip.getProvinceEnd());
        tripResponse.setTimeStart(newTrip.getTimeStart());

        return tripResponse;
    }

    @Override
    public TripResponse updateTrip(String id, TripRequest newTripRequest) {

        Trip trip = tripRepository.findById(id).orElseThrow(()-> new RuntimeException("Trip does not exist"));

        trip.setProvinceStart(newTripRequest.getProvinceStart());
        trip.setProvinceEnd(newTripRequest.getProvinceEnd());
        trip.setTimeStart(newTripRequest.getTimeStart());
        trip.setUpdateAt(new Date());

        tripRepository.save(trip);

        TripResponse tripResponse = new TripResponse();
        tripResponse.setTripId(trip.getTripId());
        tripResponse.setProvinceStart(trip.getProvinceStart());
        tripResponse.setProvinceEnd(trip.getProvinceEnd());
        tripResponse.setTimeStart(trip.getTimeStart());

        return tripResponse;
    }

    @Override
    public Boolean deleteTrip(String id) {

        Trip trip = tripRepository.findById(id).orElseThrow(()-> new RuntimeException("Trip does not exist"));
        tripRepository.delete(trip);
        return true;
    }
}
