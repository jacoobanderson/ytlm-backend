package com.example.ytlm.repository;

import com.example.ytlm.commons.RepositoryBase;
import com.example.ytlm.entity.UserEntity;
import jakarta.ejb.Stateless;

@Stateless
public class UserRepository extends RepositoryBase {
    public UserEntity findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public void save(UserEntity user) {
        entityManager.persist(user);
    }
}
