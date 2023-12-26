package com.blogapp_api.services;

import java.util.List;

import com.blogapp_api.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto userdto);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserIdById(Integer UserId);

	List<UserDto> getAllUser();

	void deleteUser(Integer UserId);
}
