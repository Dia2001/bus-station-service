package com.busstation.controller;

import com.busstation.entities.Trip;
import com.busstation.payload.request.TripRequest;
import com.busstation.payload.response.TripResponse;
import com.busstation.repositories.TripRepository;
import com.busstation.services.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripRepository tripRepository;

    @GetMapping("")
    public ResponseEntity<?> getTrip() {
        List<Trip> trips = tripRepository.findAll();
        return ResponseEntity.ok(trips);
    }

    @PostMapping("/add")
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripRequest tripRequest) throws IOException {

        TripResponse tripResponse = tripService.createTrip(tripRequest);
        return new ResponseEntity<>(tripResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Trip> updateTrip(@RequestBody TripRequest tripRequest,
                                                   @PathVariable("id") String id){

        Trip trip = tripService.updateTrip(id,tripRequest);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable("id") String id){

        if(tripService.deleteTrip(id)){
            return new ResponseEntity<>("delete Success!", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete failed!!", HttpStatus.OK);
    }
}
