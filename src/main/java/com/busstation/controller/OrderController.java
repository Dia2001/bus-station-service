package com.busstation.controller;

import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.response.OrderDetailResponse;
import com.busstation.payload.response.OrderResponse;
import com.busstation.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9999/")
@RestController(value = "orderAPIofWeb")
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/search/order/{order_id}")
    public ResponseEntity<?> searchOrderById(@PathVariable("order_id") String orderId) {

        OrderDetailResponse orderDetailPage = orderService.searchOrderById(orderId);

        return new ResponseEntity<>(orderDetailPage, HttpStatus.OK);
    }

    @PostMapping("/add/{user_id}")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable("user_id") String userId, @RequestBody OrderDetailRequest OrderDetailRequest) {

        OrderResponse orderResponse = orderService.createOrder(userId, OrderDetailRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }
}
