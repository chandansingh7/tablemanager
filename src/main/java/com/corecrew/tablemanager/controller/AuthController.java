package com.corecrew.tablemanager.controller;

import com.corecrew.tablemanager.dtos.LoginRequest;
import com.corecrew.tablemanager.dtos.RegisterRequest;
import com.corecrew.tablemanager.models.User;
import com.corecrew.tablemanager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.authenticationResponse(loginRequest));
    }

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUsers(@RequestBody RegisterRequest registerRequest){
        if (registerRequest.getRole() == null) {
            return ResponseEntity.badRequest().body("Role must be provided");
        }
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setRole(registerRequest.getRole());
        User registeredUser = userService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
