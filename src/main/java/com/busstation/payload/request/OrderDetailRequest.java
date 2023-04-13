package com.busstation.payload.request;

import lombok.Data;

@Data
public class OrderDetailRequest {

    private Boolean status;

    private String chairId;

    private String addressStart;

    private String addressEnd;

    public Boolean getStatus() {
        return status;
    }

    public Boolean setStatus(Boolean status){
        return this.status = status;
    }
}
