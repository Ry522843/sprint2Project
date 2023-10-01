package com.blog.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.entities.APIResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIResponse> invalidInputHandler(MethodArgumentNotValidException ex)
	{
		APIResponse response = new APIResponse();
		HashMap<String, String> errorData = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().stream().forEach(error -> {
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errorData.put(fieldName, message);
		});
		response.setStatus(false);
		response.setMessage("Invalid inputs, please try again!!");
		response.setData(errorData);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
