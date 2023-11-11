package com.example.ytlm.endpoints;

import com.example.ytlm.requestbodies.LoginRequest;
import com.example.ytlm.requestbodies.RegisterRequest;
import com.example.ytlm.services.AuthenticationService;
import io.jsonwebtoken.Claims;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationEndpoint {
    @Inject
    AuthenticationService authService;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(RegisterRequest registerBody) {
        authService.register(registerBody.getEmail(), registerBody.getPassword());
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginBody) {
        String jwtToken = authService.login(loginBody.getEmail(), loginBody.getPassword());

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
    public Response validateToken(@HeaderParam("Authorization") String token) {
        if (!authService.validateToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token invalid").build();
        }

        Claims claims = authService.extractClaims(token);
        return Response.ok("Token is valid for user: " + claims.getSubject()).build();
    }
}
