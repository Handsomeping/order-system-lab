package com.example.ordersystemlab.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new LinkedHashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("message", "Validation failed");
		response.put("errors", errors);

		return response;
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("status", ex.getStatusCode().value());
		response.put("message", ex.getReason());

		return ResponseEntity.status(ex.getStatusCode()).body(response);
	}
}