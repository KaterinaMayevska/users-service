package com.myshop.serviceusers.service.implementation;

import com.myshop.serviceusers.repo.UserRepo;
import com.myshop.serviceusers.repo.model.Role;
import com.myshop.serviceusers.repo.model.User;
import com.myshop.serviceusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public List<User> fetchAllUsers() {
        return userRepo.findAll();
    }

    public User fetchUserById(long id) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);

        if (maybeUser.isPresent())
            return maybeUser.get();
        else
            throw new IllegalArgumentException("Invalid user ID");
    }

    public long createUser(String login, String name, Role role ) {
        final User user = new User(login, name, role);
        final User savedUser = userRepo.save(user);

        return savedUser.getId();
    }


    public void updateUser(long id, String login, String name, Role role) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);

        if (maybeUser.isEmpty())
            throw new IllegalArgumentException("Invalid user ID");

        final User user = maybeUser.get();
        if (login != null && !login.isBlank()) user.setLogin(login);
        if (name != null && !name.isBlank()) user.setName(name);
        if (role != null) user.setRole(role);
        userRepo.save(user);
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }
}
