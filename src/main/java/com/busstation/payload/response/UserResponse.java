package com.busstation.payload.response;

import lombok.Data;

@Data
public class UserResponse {

    private String userId;

    private String fullName;

    private  String phoneNumber;

    private  String email;

    private String address;

    private boolean status;
}
