package com.example.ytlm.repository;

import com.example.ytlm.commons.RepositoryBase;
import com.example.ytlm.entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

@Stateless
public class UserRepository extends RepositoryBase {
    public UserEntity findByEmail(String email) {
        try {
            return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(UserEntity user) {
        entityManager.persist(user);
    }
}
