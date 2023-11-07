package com.task.employee.auth;

import com.task.employee.Domain.Users;
import com.task.employee.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository repository;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = Users.builder
                .


    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {



    }
}
