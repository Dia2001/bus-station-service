package com.busstation.services.impl;

import com.busstation.entities.Account;
import com.busstation.exception.DataNotFoundException;
import com.busstation.payload.request.ChangePasswordRequest;
import com.busstation.payload.response.ApiResponse;
import com.busstation.repositories.AccountRepository;
import com.busstation.services.AccountService;
import com.busstation.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ApiResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(SecurityUtils.getUserName(),changePasswordRequest.getPasswordOld()));
        if(Objects.isNull(authentication.getPrincipal())){
           throw  new DataNotFoundException("Username or password is incorrect");
        }
        Account account=accountRepository.findByusername(authentication.getName());
        account.setPassword(passwordEncoder.encode(changePasswordRequest.getPasswordNew()));
        accountRepository.save(account);
        return new ApiResponse("Change password successfully.", HttpStatus.OK);
    }
}
