package org.example.assignment.domain.calculator;

import lombok.extern.slf4j.Slf4j;
import org.example.assignment.model.MathOperation;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChainedCalculator {
	private final Calculator calculator;
	private Number curr;

	public ChainedCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public ChainedCalculator start(Number initialValue) {
		this.curr = initialValue;
		return this;
	}

	public ChainedCalculator apply(MathOperation mathOperation, Number operand) {
		curr = calculator.calculate(mathOperation, curr, operand);
		return this;
	}

	public Number getResult() {
		return curr;
	}
}