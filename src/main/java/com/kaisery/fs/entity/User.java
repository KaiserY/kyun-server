package com.kaisery.fs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class User {

    @Id
    private String id;

    private String userName;

    private String password;

    private List<Role> roles;

    private List<Permission> permissions;

    private String token;

    private Long space;

    private LocalDateTime createdTime;

    private FolderBrief rootFolder;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getSpace() {
        return space;
    }

    public void setSpace(Long space) {
        this.space = space;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public FolderBrief getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(FolderBrief rootFolder) {
        this.rootFolder = rootFolder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
