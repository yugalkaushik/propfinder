package com.propfinder.user.dto;

import com.propfinder.user.Role;
import com.propfinder.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserResponse {

    private UUID id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;

    public UserResponse() {
    }

    // This constructor converts a User entity into a safe response object,
    // deliberately leaving out passwordHash and phone, whichever fields
    // we don't want going out over the API.
    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}