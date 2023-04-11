package com.busstation.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private Date dateStart;
    private Date dateEnd;
    private String reason;
    private boolean approved;
}
