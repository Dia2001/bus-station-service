package com.busstation.controller;

import com.busstation.payload.request.ChangePasswordRequest;
import com.busstation.payload.request.SignupRequest;
import com.busstation.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:/9999/")
@RestController(value = "accountAPIofWeb")
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return new ResponseEntity<>( accountService.changePassword(changePasswordRequest), HttpStatus.OK);
    }
}
