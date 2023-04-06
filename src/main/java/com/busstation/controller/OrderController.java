package com.busstation.controller;

import com.busstation.entities.Order;
import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.request.TripRequest;
import com.busstation.payload.response.OrderDetailResponse;
import com.busstation.payload.response.OrderResponse;
import com.busstation.payload.response.TripResponse;
import com.busstation.repositories.OrderRepository;
import com.busstation.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/search/order/{order_id}")
    public ResponseEntity<?> searchOrderById(@PathVariable("order_id") String orderId,
                                                         @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        OrderDetailResponse orderDetailPage = orderService.searchOrderById(orderId);

        return new ResponseEntity<>(orderDetailPage, HttpStatus.OK);
    }

    @PostMapping("/add/{user_id}")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable("user_id") String userId, @RequestBody OrderDetailRequest OrderDetailRequest) {

        OrderResponse orderResponse = orderService.createOrder(userId, OrderDetailRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }
}
