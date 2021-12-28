package com.myshop.serviceusers.service;

import com.myshop.serviceusers.repo.model.Role;
import com.myshop.serviceusers.repo.model.User;

import java.util.List;


public interface UserService {

    List<User> fetchAllUsers();
    User fetchUserById(long id) throws IllegalArgumentException;
    long createUser(String login, String name, Role role);
    void updateUser(long id, String login, String name, Role role) throws IllegalArgumentException;
    void deleteUser(long id);
}
