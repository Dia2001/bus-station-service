package com.busstation.payload.response;

import com.busstation.entities.Trip;
import com.busstation.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserByTripIdResponse {

    private TripResponse trip;

    private List<UserResponse> user;

    public UserByTripIdResponse(User user, Trip trip) {

        TripResponse tripRes = new TripResponse();
        tripRes.setTripId(trip.getTripId());
        tripRes.setProvinceStart(trip.getProvinceStart());
        tripRes.setProvinceEnd(trip.getProvinceEnd());
        tripRes.setTimeStart(trip.getTimeStart());

        this.trip = tripRes;

        List<UserResponse> userResponses = new ArrayList<>();

        UserResponse userRes = new UserResponse();
        userRes.setUserId(user.getUserId());
        userRes.setFullName(user.getFullName());
        userRes.setEmail(user.getEmail());
        userRes.setPhoneNumber(user.getPhoneNumber());
        userRes.setAddress(user.getAddress());
        userRes.setStatus(user.getStatus());

        userResponses.add(userRes);
        this.user = userResponses;
    }
}
