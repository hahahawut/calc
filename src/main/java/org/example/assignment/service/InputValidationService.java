package org.example.assignment.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.assignment.model.MathOperation;
import org.example.assignment.exception.InvalidParameterException;
import org.example.assignment.model.CalculateRequest;
import org.example.assignment.model.ChainRequest;
import org.example.assignment.model.OperationRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InputValidationService {

	// Validates a single calculate request
	public void validateCalculateRequest(CalculateRequest calculateRequest) {
		validateOperation(calculateRequest.getOp());
		validateNumbers(calculateRequest.getNum1(), calculateRequest.getNum2());
	}

	// Validates a chain of operations request
	public void validateChainRequest(ChainRequest chainRequest) {
		if (chainRequest == null) {
			logAndThrowError("Chain request cannot be null.");
		}
		validateOperationsList(chainRequest.getOperations());
	}

	// Validates the operation from the request
	public void validateOperation(String operationStr) {
		MathOperation mathOperation = parseOperation(operationStr);
		if (mathOperation == null) {
			logAndThrowError("Unsupported operation: " + operationStr);
		}
	}

	// Validates two numbers, ensuring none are null and divisor is not zero
	public void validateNumbers(Number num1, Number num2) {
		if (num1 == null || num2 == null) {
			logAndThrowError("Numbers cannot be null.");
		}
		if (num2.doubleValue() == 0.0) {
			logAndThrowError("Divisor cannot be 0.");
		}
	}

	// Validates a list of operation requests
	public void validateOperationsList(List<OperationRequest> operations) {
		if (operations == null || operations.isEmpty()) {
			logAndThrowError("Operation list cannot be empty.");
		}
		operations.forEach(this::validateOperationRequest);
	}

	// Validates an individual operation request
	private void validateOperationRequest(OperationRequest operationRequest) {
		validateOperation(operationRequest.getOp());
		validateNumber(operationRequest.getNum());
	}

	// Ensures a single number is not null
	private void validateNumber(Number num) {
		if (num == null) {
			logAndThrowError("Number cannot be null.");
		}
	}

	// Parses the string to MathOperation, logs error if invalid
	private MathOperation parseOperation(String operationStr) {
		try {
			return MathOperation.valueOf(operationStr.toUpperCase());
		} catch (IllegalArgumentException e) {
			log.error("Invalid operation: " + operationStr, e);
			return null;
		}
	}

	// Helper method to log errors and throw InvalidParameterException
	private void logAndThrowError(String message) {
		log.error(message);
		throw new InvalidParameterException(message);
	}
}
