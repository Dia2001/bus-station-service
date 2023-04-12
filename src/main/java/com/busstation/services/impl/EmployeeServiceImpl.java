package com.busstation.services.impl;

import com.busstation.dto.EmployeeDTO;
import com.busstation.entities.Employee;
import com.busstation.entities.User;
import com.busstation.enums.NameRoleEnum;
import com.busstation.exception.DataNotFoundException;
import com.busstation.payload.request.EmployeeRequest;
import com.busstation.payload.response.ApiResponse;
import com.busstation.repositories.EmployeeRepository;
import com.busstation.repositories.custom.UserRepositoryCustom;
import com.busstation.services.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;
    @Override
    public Page<EmployeeDTO> getAlLEmployee(String keyword, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<User> users =userRepositoryCustom.getFilter(keyword, NameRoleEnum.ROLE_EMPLOYEE.toString(), pageable);
        Page<EmployeeDTO> employeeDtoList = users.map(EmployeeDTO::new);
        return employeeDtoList;
    }

    public Page<EmployeeDTO> getAlLDriver(String keyword, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<User> users =userRepositoryCustom.getFilter(keyword, NameRoleEnum.ROLE_DRIVER.toString(), pageable);
        Page<EmployeeDTO> employeeDtoList = users.map(EmployeeDTO::new);
        return employeeDtoList;
    }

    @Override
    @Transactional
    public ApiResponse edit(String id, EmployeeRequest employeeRequest) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new DataNotFoundException("Can't find this employee"));
        System.out.println("aaa");
        employee.setDob(employeeRequest.getDob());
        employee.setYoe(employeeRequest.getYoe());
        employeeRepository.save(employee);
        return new ApiResponse("Updated successfully", HttpStatus.OK);
    }
}
