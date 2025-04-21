package com.corecrew.tablemanager.dtos;

import com.corecrew.tablemanager.models.enums.UserRole;
import lombok.Data;

@Data
public class UserUpdateResponse {

    private String username;
    private String email;
    private UserRole role;
}
