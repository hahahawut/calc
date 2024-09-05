package org.example.assignment.exception;

import org.example.assignment.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<Response<String>> handleBadParameterException(InvalidParameterException ex) {
		Response<String> response = new Response<>(HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<Response<String>> handleGeneralException(Exception ex) {
		String message = ex.getMessage() != null ? ex.getMessage() : "An error occurred";
		Response<String> response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), message, null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Response<String>> handleArithmeticException(Throwable throwable) {
		String message = throwable.getMessage() != null ? throwable.getMessage() : "An arithmetic error occurred";
		Response<String> response = new Response<>(HttpStatus.BAD_REQUEST.toString(), message, null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
