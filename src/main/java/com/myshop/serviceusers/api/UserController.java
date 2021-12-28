package com.myshop.serviceusers.api;

import com.myshop.serviceusers.api.dto.UserDto;
import com.myshop.serviceusers.repo.model.Role;
import com.myshop.serviceusers.repo.model.User;
import com.myshop.serviceusers.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public final class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping
    public ResponseEntity<List<User>> index() {
        final List<User> users = userServiceImpl.fetchAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showById(@PathVariable long id) {
        try {
            final User user = userServiceImpl.fetchUserById(id);

            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDto userDto) {
        final String login = userDto.getLogin();
        final String name = userDto.getName();
        final Role role = userDto.getRole();
        final Long userId = userServiceImpl.createUser(login, name, role);
        final String userUri = String.format("/users/%d", userId);

        return ResponseEntity.created(URI.create(userUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody UserDto userDto) {
        final String login = userDto.getLogin();
        final String name = userDto.getName();
        final Role role = userDto.getRole();

        try {
            userServiceImpl.updateUser(id, login, name, role);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<UserDto> getDtoById(@PathVariable Long id) {
        try {
            User user = userServiceImpl.fetchUserById(id);
            UserDto userDto = new UserDto(user.getLogin(),user.getName(), user.getRole());

            return ResponseEntity.ok(userDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        userServiceImpl.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
