package com.stark.user_details.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stark.user_details.DTO.UserDTO;
import com.stark.user_details.entity.User;
import com.stark.user_details.exception.ResourceNotFoundException;
import com.stark.user_details.service.UserService;

@RestController
public class UserController {

	@Autowired
	public UserService userService;

	@GetMapping(value = "/user")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping(value = "/user/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws ResourceNotFoundException {
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@PostMapping(value = "/user")
	public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
		return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.OK);
	}

	@PutMapping(value = "/user/{username}")
	public ResponseEntity<String> updateUserByUsername(@PathVariable String username, @RequestBody UserDTO userDTO) throws ResourceNotFoundException {
		return new ResponseEntity<>(userService.updateUserByUsername(username, userDTO), HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/{username}")
	public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) throws ResourceNotFoundException {
		return new ResponseEntity<>(userService.deleteUserByUsername(username), HttpStatus.OK);
	}
}
