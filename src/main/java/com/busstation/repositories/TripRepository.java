package com.busstation.repositories;

import com.busstation.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,String> {
}
