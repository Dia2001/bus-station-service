package com.busstation.controller;


import com.busstation.payload.request.LeaveRequest;
import com.busstation.payload.response.CarResponse;
import com.busstation.payload.response.LeaveResponse;
import com.busstation.repositories.LeaveRepository;
import com.busstation.services.LeaveService;
import org.apache.el.parser.BooleanNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:9999/")
@RestController(value = "leaveAPIofWeb")
@RequestMapping("/api/v1/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private LeaveRepository leaveRepository;

    @GetMapping
    public ResponseEntity<?> getAllLeave(){


        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "LIST OF LEAVES");
        response.put("LEAVES", leaveService.getAllLeave());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addLeave(@RequestBody LeaveRequest request){
        return new ResponseEntity<>(leaveService.addLeave(request) ,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLeave(@RequestBody LeaveRequest request, @PathVariable("id") String leaveId){
        return new ResponseEntity<>(leaveService.updatedLeave(leaveId, request), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLeave(@PathVariable("id") String id){
        return new ResponseEntity<>(leaveService.deleteLeave(id), HttpStatus.OK);
    }
}
