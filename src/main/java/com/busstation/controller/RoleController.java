package com.busstation.controller;

import com.busstation.payload.request.RoleRequest;
import com.busstation.payload.response.RoleResponse;
import com.busstation.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9999/")
@RestController(value = "roleAPIofWeb")
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<?> createTrip(@RequestBody RoleRequest roleRequest) {

        RoleResponse roleResponse = roleService.createRole(roleRequest);
        return new ResponseEntity<>(roleResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTrip(@RequestBody RoleRequest roleRequest,
                                        @PathVariable("id") String id) {

        RoleResponse roleResponse = roleService.updateRole(id, roleRequest);
        return new ResponseEntity<>(roleResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable("id") String id) {

        if (roleService.deleteRole(id)) {
            return new ResponseEntity<>("delete Success!", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete failed!!", HttpStatus.OK);
    }
}
