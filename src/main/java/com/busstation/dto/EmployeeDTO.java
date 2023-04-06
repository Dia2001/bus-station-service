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
//        this.user.setUserId(user.getUserId());
//        this.user.setFullName(user.getFullName());
//        this.user.setPhoneNumber(user.getPhoneNumber());
//        this.user.setEmail(user.getEmail());
//        this.user.setAddress(user.getAddress());
//        this.user.setStatus(user.getStatus());
//        this.user.setCreatedAt(user.getCreatedAt());
//        this.user.setUpdatedAt(user.getUpdatedAt());
        this.dob=user.getEmployee().getDob();
        this.yoe=user.getEmployee().getYoe();
    }
}
