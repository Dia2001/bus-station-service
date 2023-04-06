package com.busstation.payload.request;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SignupRequest {

    private String username;

    private String password;

    private String role;

    private UserRequest user;


    private EmployeeRequest employee;
}
