package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleCategoryNotFound(CategoryNotFoundException ex) {

		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleProductNotFound(ProductNotFoundException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyDtoException.class)
	public ResponseEntity<Map<String, String>> handleEmptyDto(EmptyDtoException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CategoryAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductAlreadyInactiveException.class)
	public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
			ProductAlreadyInactiveException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("message", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
