package com.devteria.jpa.controller;

import com.devteria.jpa.dto.response.ApiResponse;
import com.devteria.jpa.dto.request.UserCreationRequest;
import com.devteria.jpa.dto.request.UserUpdateRequest;
import com.devteria.jpa.dto.response.UserResponse;
import com.devteria.jpa.entity.User;
import com.devteria.jpa.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping("/create")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        UserResponse userResponse = userService.createUser(request);
        return ApiResponse.<UserResponse>builder().result(userResponse).build();
    }

    @GetMapping("/list")
    public ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<UserResponse> users = userService.getUsers();
        return ApiResponse.<List<UserResponse>>builder().result(users).build();
    }

    @GetMapping("/id/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("userId") String userId) {
        UserResponse userResponse = userService.getUserById(userId);
        return ApiResponse.<UserResponse>builder().result(userResponse).build();
    }
    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        UserResponse userResponse = userService.getMyInfo();
        return ApiResponse.<UserResponse>builder().result(userResponse).build();
    }

    @PutMapping("/update/{userId}")
    public ApiResponse<UserResponse> updateUserById(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        UserResponse userResponse = userService.updateUserById(userId, request);
        return ApiResponse.<UserResponse>builder().result(userResponse).build();
    }

    @DeleteMapping("/delete/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }
}
