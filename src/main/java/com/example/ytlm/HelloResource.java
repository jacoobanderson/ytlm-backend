package com.example.ytlm;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    // Example using JPA EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    public Response testDatabaseConnection() {
        try {
            // Perform a simple query
            Query query = entityManager.createQuery("SELECT COUNT(*) FROM SectionsEntity");
            Long count = (Long) query.getSingleResult();

            return Response.ok("Database connection successful. Found " + count + " records.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error testing database connection: " + e.getMessage())
                    .build();
        }
    }
}