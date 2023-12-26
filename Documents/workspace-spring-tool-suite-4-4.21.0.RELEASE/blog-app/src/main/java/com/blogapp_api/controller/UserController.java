package com.blogapp_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogapp_api.payloads.ApiResponse;
import com.blogapp_api.payloads.UserDto;
import com.blogapp_api.services.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

	// Assuming you have a UserService
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// POST - Create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}

	// PUT - Update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable Integer userId, @RequestBody UserDto userDto) {
		UserDto updatedUser = userService.updateUser(userDto, userId);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// DELETE - Delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("user deleted sucessfully",true), HttpStatus.OK);
	}

	// GET - Get all users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUser();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getsingleUser(@PathVariable Integer userId) {
		UserDto users = userService.getUserIdById(userId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
