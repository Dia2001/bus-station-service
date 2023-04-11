package com.busstation.payload.request;

import lombok.Data;

@Data
public class OrderDetailRequest {

    private String status;

    private String chairId;

    private String addressStart;

    private String addressEnd;
}
