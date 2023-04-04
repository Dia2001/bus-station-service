package com.busstation.payload.response;

import com.busstation.entities.User;
import lombok.Data;

@Data
public class OrderResponse {

    private String orderID;

    private UserResponse user;

    private OrderDetailResponse orderDetail;
}
