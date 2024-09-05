package org.example.assignment.domain.calculator.strategy.MultiplyOperation;

import java.math.BigDecimal;

import org.example.assignment.domain.calculator.strategy.Operation;

public class BigDecimalMultiplyOperation implements Operation {
	@Override
	public Number execute(Number num1, Number num2) {
		BigDecimal bd1 = toBigDecimal(num1);
		BigDecimal bd2 = toBigDecimal(num2);
		BigDecimal result = bd1.multiply(bd2);
		return result;
	}

	private BigDecimal toBigDecimal(Number number) {
		if (number == null) {
			throw new IllegalArgumentException("Number cannot be null");
		}
		if (number instanceof BigDecimal) {
			return (BigDecimal) number;
		} else if (number instanceof Double || number instanceof Float) {
			return BigDecimal.valueOf(number.doubleValue());
		} else {
			return new BigDecimal(number.toString());
		}
	}
}