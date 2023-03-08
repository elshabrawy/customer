package com.shabrawy.customer.service;

import com.shabrawy.customer.model.Customer;
import com.shabrawy.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepository userRepository;
    //    private final PasswordEncoder encoder;

//    private final ModelMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User Not Found with email: " + email));
    }

//    public Long create(UserCreationRequest userCreationRequest, ERole... eRole) {s
//        Set<Role> roles = stream(eRole).map(e -> roleRepository.findById(e.getId()).get())
//                        .collect(toSet());
//        String encryptedPassword = encoder.encode(userCreationRequest.getPassword());
//        User user = User.of(userCreationRequest, encryptedPassword, roles);
//        return userRepository.save(user).getId();
//    }

    public List<Customer> getAllUsers() {
        return userRepository.findAll();
    }
}
