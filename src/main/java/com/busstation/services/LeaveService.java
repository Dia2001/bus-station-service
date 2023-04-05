package com.busstation.services;

import com.busstation.entities.Leave;
import com.busstation.payload.request.LeaveRequest;
import com.busstation.payload.response.LeaveResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {
    public List<LeaveResponse> getAllLeave();
    public LeaveResponse updatedLeave(String leaveId, LeaveRequest request);
    LeaveResponse addLeave(LeaveRequest request);
    public Boolean deleteLeave(String leaveId);
}
