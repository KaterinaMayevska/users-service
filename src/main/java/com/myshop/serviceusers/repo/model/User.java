package com.myshop.serviceusers.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String login;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public User(String login, String name, Role role) {
        this.login = login;
        this.name = name;
        this.role = role;
    }
}