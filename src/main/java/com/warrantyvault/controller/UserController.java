package com.warrantyvault.controller;

import com.warrantyvault.dto.request.UserLoginRequest;
import com.warrantyvault.dto.request.UserRegisterRequest;
import com.warrantyvault.dto.response.UserDTO;
import com.warrantyvault.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(userService.loginUser(request));
    }
}
