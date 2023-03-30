package com.busstation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busstation.entities.Order;

public interface OrderRepository extends JpaRepository<Order, String>{

}
