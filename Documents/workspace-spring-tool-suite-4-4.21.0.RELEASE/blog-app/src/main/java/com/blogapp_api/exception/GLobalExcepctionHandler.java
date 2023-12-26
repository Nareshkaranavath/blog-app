package com.blogapp_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.blogapp_api.payloads.ApiResponse;

@RestControllerAdvice
public class GLobalExcepctionHandler {

	/*
	 * @ExceptionHandler(ResourceNotFoundException.class)
	 * @ResponseStatus(HttpStatus.NOT_FOUND) public ResponseEntity<ApiResponse>
	 * resourceNotFoundExceptionHandler(ResourceNotFoundException ex) { 
	 * ApiResponse apiResponse = new ApiResponse(false, ex.getMessage()); 
	 * return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND); }
	 */
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

	}
}
