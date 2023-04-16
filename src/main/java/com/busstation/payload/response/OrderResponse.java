package com.busstation.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    private String orderID;

    private String tripId;

    private UserResponse user;

    private List<OrderDetailResponse> orderDetail;
}
