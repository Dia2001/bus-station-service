package com.busstation.services;

import com.busstation.payload.request.OrderDetailRequest;
import com.busstation.payload.response.OrderDetailResponse;
import com.busstation.payload.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface OrderService {

    OrderResponse createOrder(String userId, OrderDetailRequest orderDetailRequest);

    OrderDetailResponse searchOrderById(String orderId);
}
