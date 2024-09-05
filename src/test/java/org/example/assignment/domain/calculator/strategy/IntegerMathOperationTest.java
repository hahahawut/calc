package org.example.assignment.domain.calculator.strategy;

import org.example.assignment.domain.calculator.strategy.AddOperation.IntegerAddOperation;
import org.example.assignment.domain.calculator.strategy.DivideOperation.IntegerDivideOperation;
import org.example.assignment.domain.calculator.strategy.MultiplyOperation.IntegerMultiplyOperation;
import org.example.assignment.domain.calculator.strategy.SubtractOperation.IntegerSubtractOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerMathOperationTest {

	private Operation addOperation;
	private Operation subtractOperation;
	private Operation multiplyOperation;
	private Operation divideOperation;

	@BeforeEach
	public void setUp() {
		addOperation = new IntegerAddOperation();
		subtractOperation = new IntegerSubtractOperation();
		multiplyOperation = new IntegerMultiplyOperation();
		divideOperation = new IntegerDivideOperation();
	}

	@Test
	public void testAddOperation() {
		assertEquals(5, addOperation.execute(2, 3));
		assertEquals(0, addOperation.execute(-2, 2));
		assertEquals(-5, addOperation.execute(-2, -3));
		assertEquals(Integer.MAX_VALUE, addOperation.execute(Integer.MAX_VALUE, 0));
		assertThrows(ArithmeticException.class, () -> addOperation.execute(Integer.MAX_VALUE, 1), "Integer overflow should throw an exception");
	}

	@Test
	public void testSubtractOperation() {
		assertEquals(2, subtractOperation.execute(5, 3));
		assertEquals(-4, subtractOperation.execute(-2, 2));
		assertEquals(1, subtractOperation.execute(-2, -3));
		assertEquals(Integer.MIN_VALUE, subtractOperation.execute(Integer.MIN_VALUE, 0));
		assertThrows(ArithmeticException.class, () -> subtractOperation.execute(Integer.MIN_VALUE, 1), "Integer underflow should throw an exception");
	}

	@Test
	public void testMultiplyOperation() {
		assertEquals(6, multiplyOperation.execute(2, 3));
		assertEquals(-4, multiplyOperation.execute(-2, 2));
		assertEquals(6, multiplyOperation.execute(-2, -3));
		assertEquals(0, multiplyOperation.execute(0, Integer.MAX_VALUE));
		assertThrows(ArithmeticException.class, () -> multiplyOperation.execute(Integer.MAX_VALUE, 2), "Integer overflow should throw an exception");
	}

	@Test
	public void testDivideOperation() {
		assertEquals(2.0, divideOperation.execute(6, 3));  // Integer division
		assertEquals(-1.0, divideOperation.execute(-2, 2));
		assertEquals(1.0, divideOperation.execute(-3, -3));
		assertThrows(ArithmeticException.class, () -> divideOperation.execute(1, 0), "Division by zero should throw an exception");

	}
}
