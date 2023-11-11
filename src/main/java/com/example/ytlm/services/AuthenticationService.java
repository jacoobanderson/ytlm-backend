package com.example.ytlm.services;

import com.example.ytlm.commons.PasswordHashBean;
import com.example.ytlm.entity.UserEntity;
import com.example.ytlm.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;


import java.util.Date;

@Stateless
public class AuthenticationService {
    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordHashBean passwordHash;
    private static final String SECRET_KEY = "temp";


    public void register(String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            throw new RuntimeException("User with this email already exists");
        }

        String hashedPassword = passwordHash.hashPassword(password);
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser);
    }

    public String login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);

        if (user != null && passwordHash.verifyPassword(password, user.getPassword())) {
            return generateJwtToken(user.getEmail());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private String generateJwtToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
}
