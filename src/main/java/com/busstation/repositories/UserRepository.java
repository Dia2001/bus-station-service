package com.busstation.repositories;

import com.busstation.entities.Trip;
import com.busstation.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Page<User> findByTrips_TripId(String tripId, Pageable pageable);
}
