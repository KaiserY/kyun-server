package com.kaisery.fs.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN("ROLE_ADMIN", "admin"),
    ROLE_USER("ROLE_USER", "user");

    private String name;
    private String description;

    Role(String name, String description) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
