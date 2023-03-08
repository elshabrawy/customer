package com.shabrawy.customer.controller;

import com.shabrawy.customer.payload.AuthenticationRequest;
import com.shabrawy.customer.payload.AuthenticationResponse;
import com.shabrawy.customer.payload.RegisterRequest;
import com.shabrawy.customer.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.status(200).body(authenticationService.authentication(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(201).body(authenticationService.register(registerRequest));
    }

}
