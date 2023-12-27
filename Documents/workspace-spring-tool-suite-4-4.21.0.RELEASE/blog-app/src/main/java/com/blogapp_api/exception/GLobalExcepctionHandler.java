package com.blogapp_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String , String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String filedName =((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(filedName, message);
		});
		return new ResponseEntity<Map<String, String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
}
