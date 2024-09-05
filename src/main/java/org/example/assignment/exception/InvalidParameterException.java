package org.example.assignment.exception;

import lombok.Data;

@Data
public class InvalidParameterException extends RuntimeException {
	public InvalidParameterException(String message) {
		super(message);
	}
}
