package com.busstation.services.impl;

import com.busstation.converter.EmployeeConverter;
import com.busstation.converter.UserConverter;
import com.busstation.entities.*;
import com.busstation.enums.NameRoleEnum;
import com.busstation.enums.TokenEnum;
import com.busstation.exception.DataExistException;
import com.busstation.exception.DataNotFoundException;
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
    public ApiResponse signup(SignupRequest signupRequest) {

            String username = signupRequest.getUsername();
            if(accountRepository.existsByusername(username)){
                throw new DataExistException("This user with username: "+username+" already exist");
            }else{
                Account account=new Account();
                account.setUsername(signupRequest.getUsername());
                account.setPassword(SecurityUtils.passwordEncoder().encode(signupRequest.getPassword()));
                Role role=roleRepository.findByName(signupRequest.getRole());
                if(Objects.isNull(role)){
                    throw  new DataNotFoundException("Can't find this role");
                }
                account.setRole(role);
                accountRepository.save(account);
                User user=userConverter.converToEntity(signupRequest.getUser());
                user.setAccount(accountRepository.findById(account.getAccountId()).get());
                userRepository.save(user);
                if(Objects.nonNull(signupRequest.getEmployee())&&
                        signupRequest.getRole().equalsIgnoreCase(NameRoleEnum.ROLE_EMPLOYEE.toString())||
                        signupRequest.getRole().equalsIgnoreCase(NameRoleEnum.ROLE_DRIVER.toString())){
                    Employee employee=employeeConverter.converToEntity(signupRequest.getEmployee());
                    employee.setUser(user);
                    employeeRepository.save(employee);
                }
            }

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
