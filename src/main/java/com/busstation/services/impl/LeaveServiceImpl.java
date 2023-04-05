package com.busstation.services.impl;

import com.busstation.entities.Leave;
import com.busstation.entities.User;
import com.busstation.payload.request.LeaveRequest;
import com.busstation.payload.response.LeaveResponse;
import com.busstation.repositories.LeaveRepository;
import com.busstation.repositories.UserRepository;
import com.busstation.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {


    @Autowired
    private final LeaveRepository leaveRepository;
    @Autowired
    private final UserRepository userRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository, UserRepository userRepository) {
        this.leaveRepository = leaveRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<LeaveResponse> getAllLeave() {
        List<Leave> leaves = leaveRepository.findAll();

        List<LeaveResponse> leaveResponseList = new ArrayList<>();

        for (Leave leave : leaves){
            LeaveResponse leaveResponse = new LeaveResponse();
            leaveResponse.setLeaveId(leave.getLeaveId());
            leaveResponse.setUserId(leave.getUser().getUserId());
            leaveResponse.setReason(leave.getReason());
            leaveResponse.setDateStart(leave.getDateStart());
            leaveResponse.setDateEnd(leave.getDateEnd());

            leaveResponseList.add(leaveResponse);
        }
        return leaveResponseList;
    }

    @Override
    public LeaveResponse updatedLeave(String leaveId, LeaveRequest request) {
        Leave updateLeave = leaveRepository.findById(leaveId).orElseThrow(() -> new RuntimeException("Leave does not exist"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User does not exist"));
        updateLeave.setDateStart(request.getDateStart());
        updateLeave.setDateEnd(request.getDateEnd());
        updateLeave.setUser(user);
        leaveRepository.save(updateLeave);

        return getLeaveResponse(updateLeave);
    }

    @Override
    public LeaveResponse addLeave(LeaveRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("User does not Exist"));
        Leave leave = new Leave();
        leave.setReason(request.getReason());
        leave.setDateStart(request.getDateStart());
        leave.setDateEnd(request.getDateEnd());
        leave.setUser(user);

        leaveRepository.save(leave);

        Leave newLeave = leaveRepository.save(leave);
        return getLeaveResponse(newLeave);
    }

    private LeaveResponse getLeaveResponse(Leave newLeave) {
        LeaveResponse leaveResponse = new LeaveResponse();
        leaveResponse.setLeaveId(newLeave.getLeaveId());
        leaveResponse.setDateStart(newLeave.getDateStart());
        leaveResponse.setDateEnd(newLeave.getDateEnd());
        leaveResponse.setReason(newLeave.getReason());
        leaveResponse.setUserId(newLeave.getUser().getUserId());

        return leaveResponse;
    }

    @Override
    public Boolean deleteLeave(String leaveId) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow(()-> new RuntimeException("This id does not exist"));
        leaveRepository.delete(leave);
        return true;
    }
}
