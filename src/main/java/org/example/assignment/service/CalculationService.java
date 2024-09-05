package org.example.assignment.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.assignment.domain.calculator.Calculator;
import org.example.assignment.domain.calculator.ChainedCalculator;
import org.example.assignment.model.MathOperation;
import org.example.assignment.exception.ComputationException;
import org.example.assignment.model.CalculateRequest;
import org.example.assignment.model.ChainRequest;
import org.example.assignment.model.OperationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationService {

	private final Calculator calculator;
	private final ChainedCalculator chainedCalculator;
	private final InputValidationService inputValidationService;

	@Autowired
	public CalculationService(Calculator calculator, ChainedCalculator chainedCalculator,
							  InputValidationService inputValidationService) {
		this.calculator = calculator;
		this.chainedCalculator = chainedCalculator;
		this.inputValidationService = inputValidationService;
	}

	// Perform a single calculation
	public Number calculate(CalculateRequest calculateRequest) {
		inputValidationService.validateCalculateRequest(calculateRequest);
		MathOperation operation = inputValidationService.validateAndParseOperation(calculateRequest.getOp());

		Number result = performCalculation(operation, calculateRequest.getNum1(), calculateRequest.getNum2());
		if (result == null) {
			logAndThrowComputationException("calculate", operation, calculateRequest.getNum1(), calculateRequest.getNum2());
		}
		return result;
	}

	// Perform a chained calculation with multiple operations
	public Number calculateChain(ChainRequest chainRequest) {
		inputValidationService.validateChainRequest(chainRequest);

		Number initialValue = chainRequest.getInitialValue() != null ? chainRequest.getInitialValue() : 0;
		List<OperationRequest> operations = chainRequest.getOperations();

		try {
			chainedCalculator.start(initialValue);
			for (OperationRequest operationRequest : operations) {
				MathOperation operation = inputValidationService.validateAndParseOperation(operationRequest.getOp());
				chainedCalculator.apply(operation, operationRequest.getNum());
			}
			return chainedCalculator.getResult();
		} catch (Exception e) {
			log.error("Failed chained calculation: initialValue={}, operations={}", initialValue, operations, e);
			throw new ComputationException("Failed to execute chained calculation", e);
		}
	}

	// Helper method to perform a calculation with error handling
	private Number performCalculation(MathOperation operation, Number num1, Number num2) {
		try {
			return calculator.calculate(operation, num1, num2);
		} catch (Exception e) {
			log.error("Error during calculation: Operation={}, num1={}, num2={}", operation, num1, num2, e);
			throw new ComputationException("Failed to execute calculation", e);
		}
	}

	// Helper method to log and throw a ComputationException
	private void logAndThrowComputationException(String methodName, MathOperation operation, Number num1, Number num2) {
		log.error("Error during {}: Operation={}, num1={}, num2={}", methodName, operation, num1, num2);
		throw new ComputationException("Failed to execute calculation");
	}
}
