package com.busstation.services;

import com.busstation.dto.EmployeeDTO;
import com.busstation.payload.request.EmployeeRequest;
import com.busstation.payload.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    Page<EmployeeDTO> getAlL(String keyword, int pageNumber, int pageSize);
    ApiResponse edit(String id, EmployeeRequest employeeRequest);
}

