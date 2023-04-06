package com.busstation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String accountId;

    private String username;

    public String password;

    private Date createdAt;

    private Date updatedAt;

    private RoleDto role;

    private UserDto user;
}
