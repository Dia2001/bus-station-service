package com.busstation.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    private String orderID;

    private UserResponse user;

    private OrderDetailResponse orderDetail;
}
