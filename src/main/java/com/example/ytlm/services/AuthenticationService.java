package com.example.ytlm.services;

import com.example.ytlm.repository.UserRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Stateless
public class AuthenticationService {
    @Inject
    private UserRepository userRepository;

    @Inject
    private Pbkdf2PasswordHash passwordHash;
    private static final String SECRET_KEY = "temp";


    public void register(String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("User with this email already exists");
        }

        String hashedPassword = passwordHash.generate(password.toCharArray());
    }
}
