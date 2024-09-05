package org.example.assignment.domain.calculator;

import lombok.extern.slf4j.Slf4j;
import org.example.assignment.model.MathOperation;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChainedCalculator {
	private final Calculator calculator;
	private Number currentValue;

	public ChainedCalculator(Calculator calculator) {
		this.calculator = calculator;
		this.currentValue = 0; // Initialize with a default value (could also be null if that's preferred).
	}

	/**
	 * Starts the chain of calculations with an initial value.
	 *
	 * @param initialValue the starting value of the calculation chain.
	 * @return this ChainedCalculator instance to allow method chaining.
	 */
	public ChainedCalculator start(Number initialValue) {
		currentValue = initialValue;
		return this;
	}

	/**
	 * Applies a mathematical operation to the current value with the given operand.
	 *
	 * @param mathOperation the operation to apply.
	 * @param operand the operand to use in the operation.
	 * @return this ChainedCalculator instance to allow method chaining.
	 */
	public ChainedCalculator apply(MathOperation mathOperation, Number operand) {
		if (mathOperation == null || operand == null) {
			log.error("Operation or operand cannot be null.");
			throw new IllegalArgumentException("Operation or operand cannot be null.");
		}
		currentValue = calculator.calculate(mathOperation, currentValue, operand);
		return this;
	}

	/**
	 * Returns the current result of the chain of calculations.
	 *
	 * @return the current calculated value.
	 */
	public Number getResult() {
		return currentValue;
	}
}
