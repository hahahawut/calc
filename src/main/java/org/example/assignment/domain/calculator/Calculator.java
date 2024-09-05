package org.example.assignment.domain.calculator;

import lombok.extern.slf4j.Slf4j;
import org.example.assignment.model.MathOperation;
import org.example.assignment.domain.calculator.strategy.Operation;
import org.example.assignment.domain.calculator.strategy.StrategyManager;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class Calculator {

	public Number calculate(MathOperation mathOperation, Number num1, Number num2) {
		Operation op = StrategyManager.getStrategy(mathOperation, num1, num2);

		if (op == null) {
			throw new UnsupportedOperationException("not supported operation: " + mathOperation);
		}
		return op.execute(num1, num2);
	}
}
