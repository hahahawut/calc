package org.example.assignment.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.assignment.domain.calculator.Calculator;
import org.example.assignment.domain.calculator.ChainedCalculator;
import org.example.assignment.exception.ComputationException;
import org.example.assignment.model.MathOperation;
import org.example.assignment.model.CalculateRequest;
import org.example.assignment.model.ChainRequest;
import org.example.assignment.model.OperationRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationService {

	private final Calculator calculator;
	private final ChainedCalculator chainedCalculator;
	private final InputValidationService inputValidationService;

	public CalculationService(Calculator calculator, ChainedCalculator chainedCalculator,
							  InputValidationService inputValidationService) {
		this.calculator = calculator;
		this.chainedCalculator = chainedCalculator;
		this.inputValidationService = inputValidationService;
	}

	// Perform a single calculation
	public Number calculate(CalculateRequest calculateRequest) {
		MathOperation operation = inputValidationService.validateAndParseOperation(calculateRequest.getOp());
		Number num1 = calculateRequest.getNum1();
		Number num2 = calculateRequest.getNum2();

		return safePerformCalculation(operation, num1, num2);
	}

	// Perform a chained calculation with multiple operations
	public Number calculateChain(ChainRequest chainRequest) {
		Number initialValue = chainRequest.getInitialValue() != null ? chainRequest.getInitialValue() : 0;
		List<OperationRequest> operations = chainRequest.getOperations();

		return safePerformChainedCalculation(initialValue, operations);
	}

	// Safely perform a single calculation with error handling
	private Number safePerformCalculation(MathOperation operation, Number num1, Number num2) {
		try {
			return calculator.calculate(operation, num1, num2);
		} catch (Exception e) {
			logAndThrowComputationException("safePerformCalculation", operation, num1, num2, e);
			return null; // This return is redundant, as an exception will always be thrown before reaching here.
		}
	}

	// Safely perform a chained calculation with error handling
	private Number safePerformChainedCalculation(Number initialValue, List<OperationRequest> operations) {
		try {
			chainedCalculator.start(initialValue);
			operations.forEach(operationRequest -> {
				MathOperation operation = inputValidationService.validateAndParseOperation(operationRequest.getOp());
				chainedCalculator.apply(operation, operationRequest.getNum());
			});
			return chainedCalculator.getResult();
		} catch (Exception e) {
			log.error("Failed chained calculation: initialValue={}, operations={}", initialValue, operations, e);
			throw new ComputationException("Failed to execute chained calculation", e);
		}
	}

	// Helper method to log and throw a ComputationException
	private void logAndThrowComputationException(String methodName, MathOperation operation, Number num1, Number num2, Exception e) {
		log.error("Error during {}: Operation={}, num1={}, num2={}", methodName, operation, num1, num2, e);
		throw new ComputationException("Failed to execute calculation", e);
	}
}
