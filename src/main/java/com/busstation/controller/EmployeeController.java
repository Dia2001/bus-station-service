package com.busstation.controller;

import com.busstation.payload.request.EmployeeRequest;
import com.busstation.payload.response.ApiResponse;
import com.busstation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9999/")
@RestController(value = "employeeAPIofWeb")
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll(@RequestParam(value = "keyword", defaultValue ="") String keyword,
                                    @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(employeeService.getAlL(keyword,pageNumber,pageSize), HttpStatus.OK);
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> update(@PathVariable("employeeId") String employeeId, @RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.edit(employeeId,employeeRequest), HttpStatus.OK);
    }
}
