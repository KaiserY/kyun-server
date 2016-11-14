package com.kaisery.fs.entity;

import org.springframework.security.core.GrantedAuthority;

public enum  Permission implements GrantedAuthority {
    ALL("ALL","all");

    Permission(String name, String description) {
        this.name = name;
    }

    private String name;

    private String description;

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
