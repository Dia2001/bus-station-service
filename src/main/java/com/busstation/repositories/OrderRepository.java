package com.busstation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busstation.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{

}
