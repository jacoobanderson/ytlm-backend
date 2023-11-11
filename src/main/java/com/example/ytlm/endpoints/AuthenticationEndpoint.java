package com.example.ytlm.endpoints;

import com.example.ytlm.services.AuthenticationService;
import io.jsonwebtoken.Claims;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationEndpoint {
    @Inject
    AuthenticationService authService;

    @POST
    @Path("/register")
    public void register() {
        authService.register(email, password);
    }

    @POST
    @Path("/login")
    public Response login() {
        String jwtToken = authService.login(email, password);

        if (jwtToken == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid Credentials").build();
        }

        NewCookie cookie = new NewCookie(
                "jwt",
                jwtToken,
                "/",
                null,
                null,
                NewCookie.DEFAULT_MAX_AGE,
                true,   // Secure (true if served over HTTPS)
                true    // HTTP-only (prevents access from JavaScript)
        );
        
        return Response.ok("Login successful").cookie(cookie).header("Authorization", "Bearer " + jwtToken).build();
    }

    @GET
    @Path("/validate")
    public String validateToken(@HeaderParam("Authorization") String token) {
        if (authService.validateToken(token)) {
            Claims claims = authService.extractClaims(token);
            return "Token is valid for user: " + claims.getSubject();
        } else {
            return "Invalid token";
        }
    }
}
