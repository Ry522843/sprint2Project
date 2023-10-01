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
import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/createCategory")
	public ResponseEntity<APIResponse> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createdCategory = new CategoryDto();
		APIResponse response = new APIResponse();
		try{
			createdCategory = categoryService.createCategory(categoryDto);
			response.setStatus(true);
			response.setMessage("Category created successfuly!");
			response.setData(createdCategory);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(createdCategory);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCategory/{categoryId}")
	public ResponseEntity<APIResponse> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId)
	{
		CategoryDto updatedCategory = new CategoryDto();
		APIResponse response = new APIResponse();
		try{
			updatedCategory = categoryService.updateCategory(categoryDto,categoryId);
			response.setStatus(true);
			response.setMessage("Category updated successfuly!");
			response.setData(updatedCategory);
		}
		catch(Exception e){
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(updatedCategory);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/getCategoryById/{categoryId}")
	public ResponseEntity<APIResponse> getCategoryById(@PathVariable("categoryId") Integer categoryId)
	{
		CategoryDto Category = new CategoryDto();
		APIResponse response = new APIResponse();
		try{
			Category = categoryService.getCategoryById(categoryId);
			response.setStatus(true);
			response.setMessage("Category found!");
			response.setData(Category);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(Category);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<APIResponse> getAllCategory()
	{
		List<CategoryDto> Categorys = new ArrayList<>();
		APIResponse response = new APIResponse();
		try{
			Categorys = categoryService.getAllCategory();
			response.setStatus(true);
			response.setMessage("Categories found!");
			response.setData(Categorys);
		}
		catch(Exception e){
			response.setStatus(false);
			response.setMessage("Something went wrong, please try later!");
			response.setData(Categorys);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCategory/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId)
	{
		APIResponse response = new APIResponse();
		try{
			categoryService.deleteCategory(categoryId);
			response.setStatus(true);
			response.setMessage("Category deleted successfuly!");
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
