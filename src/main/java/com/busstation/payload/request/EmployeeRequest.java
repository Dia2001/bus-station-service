package com.busstation.payload.request;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class EmployeeRequest {
    private Date dob;

    private int yoe;
}
