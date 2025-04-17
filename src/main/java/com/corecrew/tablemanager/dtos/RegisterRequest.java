package com.corecrew.tablemanager.dtos;

import com.corecrew.tablemanager.models.enums.UserRole;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private UserRole role;
}
