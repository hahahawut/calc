package org.example.assignment.exception;

import lombok.Data;

@Data
public class ComputationException extends RuntimeException{
	public ComputationException(String message) {
		super(message);
	}
	public ComputationException(String message, Throwable cause) {
		super(message, cause);
	}
	public ComputationException(Throwable cause) {
		super(cause);
	}
}
