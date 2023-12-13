package com.example.user.controller;

import com.example.user.dto.UserRequest;
import com.example.user.enums.Tenant;
import com.example.user.models.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{tenant}/user")
    private List<User> getUsersByTenant(@PathVariable("tenant") String tenant) {
        return this.userService.getUsersByTenant(tenant);
    }

    @GetMapping("/{user_id}/user")
    private Optional<User> getUserByUserId(@PathVariable("user_id") String userId) {
        return this.userService.getUserByUserId(userId);
    }

    @PostMapping("/add-user")
    private Boolean addUser(@RequestBody UserRequest userRequest) {
        return this.userService.addUser(userRequest);
    }
}
