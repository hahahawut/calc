package org.example.assignment.domain.calculator.strategy.AddOperation;


import java.math.BigDecimal;

import org.example.assignment.domain.calculator.strategy.Operation;

public class BigDecimalAddOperation implements Operation {

	@Override
	public Number execute(Number num1, Number num2) {
		BigDecimal bd1 = toBigDecimal(num1);
		BigDecimal bd2 = toBigDecimal(num2);
		return bd1.add(bd2);
	}

	private BigDecimal toBigDecimal(Number number) {
		if (number instanceof BigDecimal) {
			return (BigDecimal) number;
		} else {
			return new BigDecimal(number.toString());
		}
	}
}