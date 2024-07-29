package com.devteria.jpa.mapper;

import com.devteria.jpa.dto.request.UserCreationRequest;
import com.devteria.jpa.dto.request.UserUpdateRequest;
import com.devteria.jpa.dto.response.UserResponse;
import com.devteria.jpa.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user , UserUpdateRequest request);
}
