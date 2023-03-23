package com.codecacola.springboot.auth;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.codecacola.springboot.security.JwtService;
import com.codecacola.springboot.user.Role;
import com.codecacola.springboot.user.User;
import com.codecacola.springboot.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServiceTest {


    @Test
    void register() {

    }

    @Test
    void authenticate() {

    }
}