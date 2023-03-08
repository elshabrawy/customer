package com.shabrawy.customer.controller;
import com.shabrawy.customer.model.Customer;
import com.shabrawy.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService userService;
    @GetMapping("/all-user")
    public ResponseEntity<List<Customer>> sayHello() {

        return ResponseEntity.ok(userService.getAllUsers());
    }
}
