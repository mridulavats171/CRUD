package com.task.employee.auth;

import com.task.employee.Config.JwtAuthenticationFilter;
import com.task.employee.Config.JwtService;
import com.task.employee.Config.SecurityConfiguration;
import com.task.employee.Controller.EmpController;
import com.task.employee.DTO.EmployeeDTO;
import com.task.employee.Security.Role;
import com.task.employee.Security.UserRepository;
import com.task.employee.Security.Users;
import com.task.employee.token.Token;
import com.task.employee.token.TokenRepository;
import com.task.employee.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static org.springframework.security.core.userdetails.User.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Users.builder()
                .first_name(request.getFirstname())
                .last_name(request.getLastname())
                .role(Role.USER)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
       var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(Users user){
        var validUserToken = tokenRepository.findAllValidTokenByUser(user.getId());
        if(validUserToken.isEmpty())
            return;
            validUserToken.forEach( token -> {
                token.setRevoked(true);
                token.setExpired(true);
            });
        tokenRepository.saveAll(validUserToken);
    }
    private void saveUserToken(Users user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
}
