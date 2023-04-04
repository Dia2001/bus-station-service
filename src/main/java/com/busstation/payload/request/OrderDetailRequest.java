package com.busstation.payload.request;

import lombok.Data;

@Data
public class OrderDetailRequest {

    private String status;

    private String orderId;

    private String chairId;

    private String ticketId;
}
