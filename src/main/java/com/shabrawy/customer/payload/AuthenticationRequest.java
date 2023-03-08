package com.shabrawy.customer.payload;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class AuthenticationRequest {

    private final String email;
    private final String password;

}
