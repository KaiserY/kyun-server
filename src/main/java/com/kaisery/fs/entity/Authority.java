package com.kaisery.fs.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    PERMISSION_ALL("PERMISSION_ALL", "all"),
    ROLE_ADMIN("ROLE_ADMIN", "admin"),
    ROLE_USER("ROLE_USER", "user");

    private String name;
    private String description;

    Authority(String name, String description) {
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
