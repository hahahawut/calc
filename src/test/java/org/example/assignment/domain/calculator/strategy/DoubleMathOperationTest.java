package org.example.assignment.domain.calculator.strategy;

import static org.junit.jupiter.api.Assertions.*;
import org.example.assignment.domain.calculator.strategy.AddOperation.DoubleAddOperation;
import org.example.assignment.domain.calculator.strategy.SubtractOperation.DoubleSubtractOperation;
import org.example.assignment.domain.calculator.strategy.MultiplyOperation.DoubleMultiplyOperation;
import org.example.assignment.domain.calculator.strategy.DivideOperation.DoubleDivideOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DoubleMathOperationTest {

	private Operation addOperation;
	private Operation subtractOperation;
	private Operation multiplyOperation;
	private Operation divideOperation;

	@BeforeEach
	public void setUp() {
		addOperation = new DoubleAddOperation();
		subtractOperation = new DoubleSubtractOperation();
		multiplyOperation = new DoubleMultiplyOperation();
		divideOperation = new DoubleDivideOperation();
	}

	@Test
	public void testAddOperation() {
		assertEquals(5.0, addOperation.execute(2.0, 3.0));
		assertEquals(0.0, addOperation.execute(-2.0, 2.0));
		assertEquals(3.5, addOperation.execute(1.5, 2.0));
		assertEquals(-1.0, addOperation.execute(-0.5, -0.5)); // additional test case
		ArithmeticException exception = assertThrows(ArithmeticException.class, () -> addOperation.execute(Double.MAX_VALUE, Double.MAX_VALUE));
		assertEquals("Double overflow or invalid result", exception.getMessage());

	}

	@Test
	public void testSubtractOperation() {
		assertEquals(2.0, subtractOperation.execute(5.0, 3.0));
		assertEquals(-4.0, subtractOperation.execute(-2.0, 2.0));
		assertEquals(-0.5, subtractOperation.execute(1.5, 2.0));
		assertEquals(0.0, subtractOperation.execute(-0.5, -0.5)); // additional test case

		ArithmeticException exception = assertThrows(ArithmeticException.class, () -> subtractOperation.execute(-Double.MAX_VALUE, Double.MAX_VALUE));
		assertEquals("Overflow or invalid operation result", exception.getMessage());
	}

	@Test
	public void testMultiplyOperation() {
		assertEquals(6.0, multiplyOperation.execute(2.0, 3.0));
		assertEquals(-4.0, multiplyOperation.execute(-2.0, 2.0));
		assertEquals(3.0, multiplyOperation.execute(1.5, 2.0));
		assertEquals(0.0, multiplyOperation.execute(0.0, 5.0)); // additional test for zero
		assertThrows(ArithmeticException.class, () -> multiplyOperation.execute(1e308, 1e308));
		ArithmeticException exception = assertThrows(ArithmeticException.class, () -> multiplyOperation.execute(Double.MAX_VALUE, 2.0));
		assertEquals("DoubleMultiplyOperation: Double overflow in multiplication", exception.getMessage());
	}

	@Test
	public void testDivideOperation() {
		assertEquals(2.0, divideOperation.execute(6.0, 3.0));
		assertEquals(-1.0, divideOperation.execute(-2.0, 2.0));
		assertEquals(0.75, divideOperation.execute(1.5, 2.0));
		assertThrows(ArithmeticException.class, () -> divideOperation.execute(1.0, 0.0)); // divide by zero
		assertEquals(Double.POSITIVE_INFINITY, divideOperation.execute(1.0, Double.MIN_VALUE)); // edge case: divide by very small number
		assertThrows(ArithmeticException.class, () -> divideOperation.execute(0.0, 0.0)); // additional test for 0/0
	}

	@Test
	public void testInvalidInput() {
		assertThrows(NullPointerException.class, () -> addOperation.execute(null, 2.0)); // null input
		assertThrows(NullPointerException.class, () -> subtractOperation.execute(2.0, null));
		assertThrows(NullPointerException.class, () -> multiplyOperation.execute(null, null));
		assertThrows(NullPointerException.class, () -> divideOperation.execute(null, 1.0));
	}

	@Test
	public void testNegativeNumbers() {
		assertEquals(-5.0, addOperation.execute(-2.0, -3.0)); // additional test with negative numbers
		assertEquals(1.0, subtractOperation.execute(-2.0, -3.0));
		assertEquals(6.0, multiplyOperation.execute(-2.0, -3.0));
		assertEquals(0.6666666666666666, divideOperation.execute(-2.0, -3.0));
	}
}
