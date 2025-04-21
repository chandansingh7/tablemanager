package com.corecrew.tablemanager.services;


import com.corecrew.tablemanager.dtos.LoginRequest;
import com.corecrew.tablemanager.dtos.UserUpdateResponse;
import com.corecrew.tablemanager.exceptions.UserAlreadyExistsException;
import com.corecrew.tablemanager.models.User;
import com.corecrew.tablemanager.repository.UserRepository;
import com.corecrew.tablemanager.security.jwt.JwtAuthenticationResponse;
import com.corecrew.tablemanager.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public JwtAuthenticationResponse authenticationResponse(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

     SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt);
    }


    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("A user with this username already exists.");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    public UserUpdateResponse updateUser(User user) {
        System.out.println("in user update");
        User currentUser = userRepository.findById(user.getId()).
                orElseThrow(() -> new UserAlreadyExistsException("User Don't Exists"));

        if (!userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("A user with this username don't exists.");
        } else {
            currentUser.setId(user.getId());
            currentUser.setUsername(user.getUsername());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
            currentUser.setRole(user.getRole());
            User user1 = userRepository.save(currentUser);

            return userTOUpdatedResponseDTO(user1);
        }
    }

    public UserUpdateResponse userTOUpdatedResponseDTO(User user) {
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userUpdateResponse.setUsername(user.getUsername());
        userUpdateResponse.setEmail(user.getEmail());
        userUpdateResponse.setRole(user.getRole());
        return userUpdateResponse;
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow(
                ()-> new UsernameNotFoundException("User not found with username")
        );
    }

    public List<User> getUserList() {
        if (userRepository.findAll().isEmpty()) {
            throw new UsernameNotFoundException("No user exists.");
        }
        return userRepository.findAll();
    }
}
