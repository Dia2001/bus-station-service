package com.busstation.services;

import com.busstation.payload.request.LeaveRequest;
import com.busstation.payload.response.LeaveResponse;

import java.util.List;

public interface LeaveService {
    List<LeaveResponse> getAllLeave();

    LeaveResponse updatedLeave(String leaveId, LeaveRequest request);

    LeaveResponse addLeave(LeaveRequest request);

    Boolean deleteLeave(String leaveId);
}
