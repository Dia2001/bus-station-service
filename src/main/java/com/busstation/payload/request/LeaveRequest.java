package com.busstation.payload.request;

import com.busstation.entities.User;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private String userId;
    private Date dateStart;
    private Date dateEnd;
    private String reason;
}
