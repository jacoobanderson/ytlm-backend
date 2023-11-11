package com.example.ytlm.endpoints;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/auth")
public class AuthenticationEndpoint {
    @Inject AuthService authService;
}
