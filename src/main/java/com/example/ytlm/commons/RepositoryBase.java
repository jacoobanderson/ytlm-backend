package com.example.ytlm.commons;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
public abstract class RepositoryBase {

    @PersistenceContext
    protected EntityManager entityManager;

    // Add common methods that you want to use in all repositories
}
