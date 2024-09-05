package org.example.assignment.domain.calculator.strategy.MultiplyOperation;

import org.example.assignment.domain.calculator.strategy.Operation;

public class DoubleMultiplyOperation implements Operation {
	@Override
	public Number execute(Number num1, Number num2) {
		double result = num1.doubleValue() * num2.doubleValue();
		if (Double.isInfinite(result)) {
			throw new ArithmeticException("DoubleMultiplyOperation: Double overflow in multiplication");
		}
		return result;
	}
}