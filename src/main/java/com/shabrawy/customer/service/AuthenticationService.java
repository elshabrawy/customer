package com.shabrawy.customer.service;

import com.shabrawy.customer.payload.AuthenticationRequest;
import com.shabrawy.customer.payload.AuthenticationResponse;
import com.shabrawy.customer.payload.RegisterRequest;
import com.shabrawy.customer.enums.Role;
import com.shabrawy.customer.model.Customer;
import com.shabrawy.customer.repository.CustomerRepository;
import com.shabrawy.customer.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CustomerRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest){
        Customer user= Customer.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken=jwtUtil.generateToken(user);
        return AuthenticationResponse.builder().
                token(jwtToken)
                .build();
    }
    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword())
        );
        var user=userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken=jwtUtil.generateToken(user);
        return AuthenticationResponse.builder().
                token(jwtToken)
                .build();
    }
}
