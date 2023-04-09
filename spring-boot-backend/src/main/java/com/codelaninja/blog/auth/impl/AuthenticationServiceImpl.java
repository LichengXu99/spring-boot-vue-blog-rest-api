package com.codelaninja.blog.auth.impl;

import com.codelaninja.blog.auth.AuthenticationRequest;
import com.codelaninja.blog.auth.AuthenticationResponse;
import com.codelaninja.blog.auth.AuthenticationService;
import com.codelaninja.blog.auth.RegisterRequest;
import com.codelaninja.blog.security.JwtService;
import com.codelaninja.blog.user.Role;
import com.codelaninja.blog.user.User;
import com.codelaninja.blog.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    // Allow to create a user,then save them into database and generate the token.
    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastLogin(Date.valueOf(LocalDate.now()))
                .role(Role.ROLE_USER)
                .build();
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    // Authenticate the user by the email and password.
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        user.setLastLogin(Date.valueOf(LocalDate.now()));
        repository.save(user);

       var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
