package com.busstation.payload.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketResponse {

    private String ticketId;

    private String addressStart;

    private String addressEnd;

    private BigDecimal price;
}
