package com.busstation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busstation.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

    List<Order> findAllByTrip_TripId(String tripId);
}
