package com.busstation.services;

import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.response.OrderResponse;
import org.springframework.stereotype.Service;

public interface OrderService {

    OrderResponse createOrder(String userId, OrderDetailRequest orderDetailRequest);

}
