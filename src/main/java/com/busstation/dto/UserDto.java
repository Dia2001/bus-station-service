package com.busstation.dto;


import com.busstation.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String fullName;

    private  String phoneNumber;

    private  String email;

    private String address;

    private Boolean status;

    private Date createdAt;

    private Date updatedAt;

    private EmployeeDTO employeeDTO;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.fullName =user.getFullName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.status = user.getStatus();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
