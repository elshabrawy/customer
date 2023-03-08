package com.shabrawy.customer.payload;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class RegisterRequest {
    private final String name;
    private final String email;
    private final String password;
}
