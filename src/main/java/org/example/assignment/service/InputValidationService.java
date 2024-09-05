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
		MathOperation mathOperation = validateAndParseOperation(calculateRequest.getOp());
		Number num1 = calculateRequest.getNum1();
		Number num2 = calculateRequest.getNum2();

		validateOperation(mathOperation);
		validateNumbers(num1, num2);
	}

	// Validates a chain of operations request
	public void validateChainRequest(ChainRequest chainRequest) {
		List<OperationRequest> operations = chainRequest.getOperations();
		validateOperationsList(operations);
	}

	// Ensures the operation is not null and is supported
	public void validateOperation(MathOperation mathOperation) {
		if (mathOperation == null) {
			logAndThrowError("operation cannot be null or not supported");
		}
	}

	// Validates two numbers, ensuring none are null and divisor is not zero
	public void validateNumbers(Number num1, Number num2) {
		if (num1 == null || num2 == null) {
			logAndThrowError("Invalid input: Numbers cannot be null");
		}
		if (num2.doubleValue() == 0.0) {
			logAndThrowError("Invalid input: Divisor cannot be 0");
		}
	}

	// Validates a list of operation requests
	public void validateOperationsList(List<OperationRequest> operations) {
		if (operations == null || operations.isEmpty()) {
			logAndThrowError("Operation list cannot be null or empty");
		}

		for (OperationRequest operationRequest : operations) {
			MathOperation mathOperation = validateAndParseOperation(operationRequest.getOp());
			validateOperation(mathOperation);
			validateNumber(operationRequest.getNum());
		}
	}

	// Ensures a single number is not null
	private void validateNumber(Number num) {
		if (num == null) {
			logAndThrowError("Invalid operation request: Number cannot be null");
		}
	}

	// Converts string to MathOperation, throws error if invalid
	public MathOperation validateAndParseOperation(String operationStr) {
		try {
			return MathOperation.valueOf(operationStr.toUpperCase());
		} catch (IllegalArgumentException e) {
			logAndThrowError(operationStr + ". Supported operations are: ADD, SUBTRACT, MULTIPLY, DIVIDE.");
			return null;  // This return is technically unreachable but added for completeness
		}
	}

	// Helper method to log errors and throw InvalidParameterException
	private void logAndThrowError(String message) {
		log.error(message);
		throw new InvalidParameterException(message);
	}
}
