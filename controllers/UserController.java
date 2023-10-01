package com.blog.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.APIResponse;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/createUser")
	public ResponseEntity<APIResponse> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createdUser = new UserDto();
		APIResponse response = new APIResponse();
		try{
			createdUser = userService.createUser(userDto);
			response.setStatus(true);
			response.setMessage("User created successfuly!");
			response.setData(createdUser);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(createdUser);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<APIResponse> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer userId)
	{
		UserDto updatedUser = new UserDto();
		APIResponse response = new APIResponse();
		try{
			updatedUser = userService.updateUser(userDto,userId);
			response.setStatus(true);
			response.setMessage("User updated successfuly!");
			response.setData(updatedUser);
		}
		catch(Exception e){
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(updatedUser);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<APIResponse> getUserById(@PathVariable("userId") Integer userId)
	{
		UserDto user = new UserDto();
		APIResponse response = new APIResponse();
		try{
			user = userService.getUserById(userId);
			response.setStatus(true);
			response.setMessage("User found!");
			response.setData(user);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(user);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<APIResponse> getAllUser()
	{
		List<UserDto> users = new ArrayList<>();
		APIResponse response = new APIResponse();
		try{
			users = userService.getAllUser();
			response.setStatus(true);
			response.setMessage("Users found!");
			response.setData(users);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(users);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<APIResponse> deleteUser(@PathVariable("userId") Integer userId)
	{
		APIResponse response = new APIResponse();
		try{
			userService.deleteUser(userId);
			response.setStatus(true);
			response.setMessage("User deleted successfuly!");
		}
		catch(ResourceNotFoundException e){
			response.setStatus(false);
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
