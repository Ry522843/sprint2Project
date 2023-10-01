package com.blog.controllers;

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
import com.blog.payloads.PostDto;
import com.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@PostMapping("/createPost/user/{userId}/category/{categoryId}")
	public ResponseEntity<APIResponse> createPost(@Valid @RequestBody PostDto postDto,
			@PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId)
	{
		APIResponse response = new APIResponse();
		try
		{
			PostDto data = postService.createPost(postDto,categoryId,userId);
			response.setStatus(true);
			response.setMessage("Post created successfuly!");
			response.setData(data);
		}
		catch(ResourceNotFoundException e){
			response.setStatus(false);
			response.setMessage(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception ex)
		{
			response.setStatus(false);
			response.setMessage("Something went wrong, please try again!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllPost")
	public ResponseEntity<APIResponse> getAllPost()
	{
		APIResponse response = new APIResponse();
		try{
			List<PostDto> data = postService.getAllPost();
			response.setStatus(true);
			response.setMessage("Posts found!");
			response.setData(data);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<APIResponse> getPost(@PathVariable("postId") Integer postId)
	{
		APIResponse response = new APIResponse();
		try{
			PostDto data = postService.getPostByPostId(postId);
			response.setStatus(true);
			response.setMessage("Post found!");
			response.setData(data);
		}
		catch(ResourceNotFoundException ex){
			response.setStatus(false);
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception ex){
			ex.printStackTrace();
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePost/{postId}")
	public ResponseEntity<APIResponse> deletePost(@PathVariable("postId") Integer postId)
	{
		APIResponse response = new APIResponse();
		try{
			postService.deletePostById(postId);
			response.setStatus(true);
			response.setMessage("Post deleted successfuly!");
		}
		catch(ResourceNotFoundException ex){
			response.setStatus(false);
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<APIResponse> updatePost(@PathVariable("postId") Integer postId, @RequestBody PostDto postDto)
	{
		APIResponse response = new APIResponse();
		try{
			PostDto data = postService.updatePost(postDto,postId);
			response.setStatus(true);
			response.setMessage("Post updated successfuly!");
			response.setData(data);
		}
		catch(ResourceNotFoundException ex){
			response.setStatus(false);
			response.setMessage(ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
