package com.busstation.dto;

import com.busstation.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private UserDto user;

    private String employeeId;
    private Date dob;
    private int yoe;

    public EmployeeDTO(User user) {
        UserDto userDto=new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFullName(user.getFullName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setStatus(user.getStatus());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        this.setUser(userDto);
        this.employeeId=user.getEmployee().getEmployeeId();
        this.dob=user.getEmployee().getDob();
        this.yoe=user.getEmployee().getYoe();
    }
}
