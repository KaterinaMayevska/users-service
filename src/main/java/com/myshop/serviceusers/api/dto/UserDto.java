package com.myshop.serviceusers.api.dto;

import com.myshop.serviceusers.repo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String login;
    private String name;
    private Role role;
}
