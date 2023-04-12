package com.busstation.services;

import com.busstation.dto.EmployeeDTO;
import com.busstation.payload.request.EmployeeRequest;
import com.busstation.payload.response.ApiResponse;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Page<EmployeeDTO> getAlLEmployee(String keyword, int pageNumber, int pageSize);

    Page<EmployeeDTO> getAlLDriver(String keyword, int pageNumber, int pageSize);

    ApiResponse edit(String id, EmployeeRequest employeeRequest);
}

