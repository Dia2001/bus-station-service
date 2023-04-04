package com.busstation.payload.response;

import lombok.Data;

@Data
public class OrderDetailResponse {

    private String orderDetailId;

    private String status;

    private OrderResponse order;

    private ChairResponse chair;

    private TicketResponse ticket;
}
