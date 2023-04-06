package com.busstation.payload.response;

import lombok.*;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveResponse {
    private String leaveId;
    private String userId;
    private Date dateStart;
    private Date dateEnd;
    private String reason;
}
