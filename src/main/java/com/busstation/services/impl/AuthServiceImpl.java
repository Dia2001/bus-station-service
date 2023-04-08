package com.busstation.services.impl;

import com.busstation.common.Validate;
import com.busstation.converter.EmployeeConverter;
import com.busstation.converter.UserConverter;
import com.busstation.entities.*;
import com.busstation.enums.NameRoleEnum;
import com.busstation.enums.TokenEnum;
import com.busstation.exception.DataExistException;
import com.busstation.exception.DataNotFoundException;
import com.busstation.payload.request.EmployeeRequest;
import com.busstation.payload.request.LoginRequest;
import com.busstation.payload.request.SignupRequest;
import com.busstation.payload.response.ApiResponse;
import com.busstation.payload.response.JwtResponse;
import com.busstation.repositories.*;
import com.busstation.services.AuthService;
import com.busstation.utils.JwtProviderUtils;
import com.busstation.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProviderUtils tokenProvider;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Override
    public JwtResponse signin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateTokenUsingUserName(loginRequest.getUsername());

        var account = accountRepository.findByusername(loginRequest.getUsername());
        revokeAllUserTokens(account);
        saveUserToken(account, jwt);

        return new JwtResponse(jwt);
    }

    @Override
    @Transactional
    public ApiResponse signUpUser(SignupRequest signupRequest) {

            String username = signupRequest.getUsername();
            if(accountRepository.existsByusername(username)){
                throw new DataExistException("This user with username: "+username+" already exist");
            }else{
                Account account=new Account();
                account.setUsername(signupRequest.getUsername());
                account.setPassword(SecurityUtils.passwordEncoder().encode(signupRequest.getPassword()));
                Role role=roleRepository.findByName(NameRoleEnum.ROLE_USER.toString());
                account.setRole(role);
                accountRepository.save(account);
                User user=userConverter.converToEntity(signupRequest.getUser());
                user.setAccount(accountRepository.findById(account.getAccountId()).get());
                user.setStatus(Boolean.TRUE);
                userRepository.save(user);
            }

        return new ApiResponse("Create successfully", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ApiResponse signUpEmployee(String accountId, EmployeeRequest employeeRequest){
        Account account=accountRepository.findById(accountId).orElseThrow(() -> new DataNotFoundException("Can't find this account"));
        Role role=roleRepository.findById(employeeRequest.getRoleId()).orElseThrow(() -> new DataNotFoundException("Can't find this role"));
        account.setRole(role);
        accountRepository.save(account);
        Employee employee=new Employee();
        employee.setDob(employeeRequest.getDob());
        employee.setYoe(employeeRequest.getYoe());
        employee.setUser(account.getUser());
        employeeRepository.save(employee);
        return new ApiResponse("Create successfully", HttpStatus.CREATED);
    }

    private void saveUserToken(Account account, String jwtToken) {
        Token token = new Token();
        token.setAccount(account);
        token.setToken(jwtToken);
        token.setExpired(false);
        token.setRevoked(false);
        token.setTokenType(TokenEnum.BEARER);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Account account) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(account.getAccountId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
