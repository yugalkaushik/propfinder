package com.propfinder.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// @Repository - a Spring annotation, marks this class as a component whose job is data access.
@Repository
public class UserRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    // saves a new user or updates an existing one if it exists
    public User save(User user){
        if(user.getId() == null){
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    // find a user by their primary key(id)
    public Optional<User> findById(UUID id){
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    // find a user by email
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst();
    }

    // Checks whether a user with this email already exists, without loading
    // the full row, used at registration to reject duplicate emails early.
    public boolean existsByEmail(String email) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }   
}
