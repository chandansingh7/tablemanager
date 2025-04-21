package com.corecrew.tablemanager.controller;

import com.corecrew.tablemanager.dtos.RegisterRequest;
import com.corecrew.tablemanager.dtos.UserUpdateResponse;
import com.corecrew.tablemanager.models.User;
import com.corecrew.tablemanager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAllUserList() {

        List<User> userList = userService.getUserList();
        return ResponseEntity.status(HttpStatus.CREATED).body(userList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> getAllUserList(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        UserUpdateResponse user1 = userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }
}