package com.busstation.dto;

import com.busstation.entities.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private String accountId;

    private String username;

    public String password;

    private Date createdAt;

    private Date updatedAt;

    private RoleDto role;

    private UserDto user;

    public AccountDto(Account account) {
        this.accountId = account.getAccountId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(account.getRole().getRoleId());
        roleDto.setName(account.getRole().getName());
        this.setRole(roleDto);
        UserDto userDto = new UserDto();
        userDto.setUserId(account.getUser().getUserId());
        userDto.setFullName(account.getUser().getFullName());
        userDto.setPhoneNumber(account.getUser().getPhoneNumber());
        userDto.setEmail(account.getUser().getEmail());
        userDto.setAddress(account.getUser().getAddress());
        userDto.setStatus(account.getUser().getStatus());
        userDto.setCreatedAt(account.getUser().getCreatedAt());
        userDto.setUpdatedAt(account.getUser().getUpdatedAt());
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(account.getUser().getEmployee().getEmployeeId());
        employeeDTO.setDob(account.getUser().getEmployee().getDob());
        employeeDTO.setYoe(account.getUser().getEmployee().getYoe());
        userDto.setEmployeeDTO(employeeDTO);
        this.setUser(userDto);
    }

}
