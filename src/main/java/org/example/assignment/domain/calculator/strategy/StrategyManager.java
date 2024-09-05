package org.example.assignment.domain.calculator.strategy;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.example.assignment.model.MathOperation;
import org.example.assignment.domain.calculator.strategy.AddOperation.BigDecimalAddOperation;
import org.example.assignment.domain.calculator.strategy.AddOperation.DoubleAddOperation;
import org.example.assignment.domain.calculator.strategy.AddOperation.IntegerAddOperation;
import org.example.assignment.domain.calculator.strategy.DivideOperation.BigDecimalDivideOperation;
import org.example.assignment.domain.calculator.strategy.DivideOperation.DoubleDivideOperation;
import org.example.assignment.domain.calculator.strategy.DivideOperation.IntegerDivideOperation;
import org.example.assignment.domain.calculator.strategy.MultiplyOperation.BigDecimalMultiplyOperation;
import org.example.assignment.domain.calculator.strategy.MultiplyOperation.DoubleMultiplyOperation;
import org.example.assignment.domain.calculator.strategy.MultiplyOperation.IntegerMultiplyOperation;
import org.example.assignment.domain.calculator.strategy.SubtractOperation.BigDecimalSubtractOperation;
import org.example.assignment.domain.calculator.strategy.SubtractOperation.DoubleSubtractOperation;
import org.example.assignment.domain.calculator.strategy.SubtractOperation.IntegerSubtractOperation;

@Slf4j
public class StrategyManager {

	public static Operation getStrategy(MathOperation mathOperation, Number num1, Number num2) {
		if (mathOperation == MathOperation.ADD) {
			if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
				return new BigDecimalAddOperation();
			} else if (num1 instanceof Double || num2 instanceof Double) {
				return new DoubleAddOperation();
			} else if (num1 instanceof Integer && num2 instanceof Integer) {
				return new IntegerAddOperation();
			}
		} else if (mathOperation == MathOperation.SUBTRACT) {
			if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
				return new BigDecimalSubtractOperation();
			} else if (num1 instanceof Double || num2 instanceof Double) {
				return new DoubleSubtractOperation();
			} else if (num1 instanceof Integer && num2 instanceof Integer) {
				return new IntegerSubtractOperation();
			}
		} else if (mathOperation == MathOperation.MULTIPLY) {
			if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
				return new BigDecimalMultiplyOperation();
			} else if (num1 instanceof Double || num2 instanceof Double) {
				return new DoubleMultiplyOperation();
			} else if (num1 instanceof Integer && num2 instanceof Integer) {
				return new IntegerMultiplyOperation();
			}
		} else if (mathOperation == MathOperation.DIVIDE) {
			if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
				return new BigDecimalDivideOperation();
			} else if (num1 instanceof Double || num2 instanceof Double) {
				return new DoubleDivideOperation();
			} else if (num1 instanceof Integer && num2 instanceof Integer) {
				return new IntegerDivideOperation();
			}
		}

		log.info("Unsupported operation or number type, num1: {}, num2: {}", num1.getClass(), num2.getClass());
		throw new UnsupportedOperationException("Unsupported operation or number type");
	}
}