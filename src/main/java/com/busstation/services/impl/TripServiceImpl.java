package com.busstation.services.impl;

import com.busstation.entities.Car;
import com.busstation.entities.Trip;
import com.busstation.entities.User;
import com.busstation.payload.request.SearchTripRequest;
import com.busstation.payload.request.TicketRequest;
import com.busstation.payload.request.TripRequest;
import com.busstation.payload.response.SearchTripResponse;
import com.busstation.payload.response.TripResponse;
import com.busstation.payload.response.UserByTripIdResponse;
import com.busstation.repositories.CarRepository;
import com.busstation.repositories.TripRepository;
import com.busstation.repositories.UserRepository;
import com.busstation.services.TicketService;
import com.busstation.services.TripService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TicketService ticketService;

    @Override
    public TripResponse createTrip(TripRequest tripRequest) {

        Trip trip = new Trip();
        trip.setProvinceStart(tripRequest.getProvinceStart());
        trip.setProvinceEnd(tripRequest.getProvinceEnd());
        trip.setTimeStart(tripRequest.getTimeStart());

        Trip newTrip = tripRepository.save(trip);

        TicketRequest ticketRequest = new TicketRequest(tripRequest.getProvinceStart(),
                tripRequest.getProvinceEnd(),
                tripRequest.getPrice());
        ticketService.addTicket(ticketRequest);

        TripResponse tripResponse = new TripResponse();
        tripResponse.setTripId(newTrip.getTripId());
        tripResponse.setProvinceStart(newTrip.getProvinceStart());
        tripResponse.setProvinceEnd(newTrip.getProvinceEnd());
        tripResponse.setTimeStart(newTrip.getTimeStart());

        return tripResponse;
    }

    @Override
    public TripResponse updateTrip(String id, TripRequest newTripRequest) {

        Trip trip = tripRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trip does not exist"));

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

        Trip trip = tripRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trip does not exist"));

        List<Car> cars = carRepository.findByTrips_TripId(id);

        for (Car car : cars) {
            car.setTrips(null);
            car.setStatus(false);
            carRepository.save(car);
        }
        tripRepository.delete(trip);
        deleteUserToTrip(id);
        return true;
    }

    @Override
    public Page<SearchTripResponse> getAllTripsByProvinceStartAndProvinceEndDateTime(SearchTripRequest searchTripRequest,
                                                                                     int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createAt").ascending());

        if (searchTripRequest.getProvinceStart() == null && searchTripRequest.getProvinceEnd() == null) {

            Page<Trip> trips = tripRepository.findAllTrips(pageable);


            return trips.map(SearchTripResponse::new);
        }

        if (searchTripRequest.getDateTime() == null) {

            Page<Trip> trips = tripRepository.findByProvinceStartAndProvinceEnd(searchTripRequest.getProvinceStart(),
                    searchTripRequest.getProvinceEnd(), pageable);

            return trips.map(SearchTripResponse::new);
        }

        Page<Trip> trips = tripRepository.findByProvinceStartAndProvinceEndAndDateTime(searchTripRequest.getProvinceStart(),
                searchTripRequest.getProvinceEnd(),
                searchTripRequest.getDateTime(), pageable);

        return trips.map(SearchTripResponse::new);
    }

    @Override
    public Page<TripResponse> getAllTrips(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createAt").ascending());

        Page<Trip> trips = tripRepository.findAll(pageable);

        return trips.map(TripResponse::new);
    }

    @Override
    public Page<UserByTripIdResponse> getAllUserByTrip(String tripId, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<User> users = userRepository.findByTrips_TripId(tripId, pageable);

        return users.map(user -> new UserByTripIdResponse(user, user.getTrips().stream()
                .filter(trip -> trip.getTripId().equals(tripId))
                .findFirst().orElse(null)));
    }

    public void deleteUserToTrip(String tripId) {
        List<User> users = userRepository.findAllByTrips_TripId(tripId);
        if (!users.isEmpty()) {

            Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Trip not found"));

            for (User itemUser : users) {

                User user = userRepository.findById(itemUser.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

                trip.getUsers().remove(user);
            }
            tripRepository.save(trip);
        }
    }
}
