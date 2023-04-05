package com.busstation.controller;

import com.busstation.entities.Trip;
import com.busstation.payload.request.SearchTripRequest;
import com.busstation.payload.request.TripRequest;
import com.busstation.payload.response.SearchTripResponse;
import com.busstation.payload.response.TripResponse;
import com.busstation.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/search")
    public ResponseEntity<?> getAllTripsByProvinceStartAndProvinceEndDateTime(
            @RequestBody SearchTripRequest searchTripRequest,
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        Page<SearchTripResponse> trips = tripService.getAllTripsByProvinceStartAndProvinceEndDateTime(searchTripRequest,pageNo,pageSize);

        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripRequest tripRequest){

        TripResponse tripResponse = tripService.createTrip(tripRequest);
        return new ResponseEntity<>(tripResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TripResponse> updateTrip(@RequestBody TripRequest tripRequest,
                                                   @PathVariable("id") String id){

        TripResponse trip = tripService.updateTrip(id,tripRequest);
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
