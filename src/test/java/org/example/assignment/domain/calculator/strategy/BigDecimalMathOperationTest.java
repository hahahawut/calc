package org.example.assignment.domain.calculator.strategy;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.example.assignment.domain.calculator.strategy.AddOperation.BigDecimalAddOperation;
import org.example.assignment.domain.calculator.strategy.SubtractOperation.BigDecimalSubtractOperation;
import org.example.assignment.domain.calculator.strategy.MultiplyOperation.BigDecimalMultiplyOperation;
import org.example.assignment.domain.calculator.strategy.DivideOperation.BigDecimalDivideOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BigDecimalMathOperationTest {

	private Operation addOperation;
	private Operation subtractOperation;
	private Operation multiplyOperation;
	private Operation divideOperation;

	@BeforeEach
	public void setUp() {
		addOperation = new BigDecimalAddOperation();
		subtractOperation = new BigDecimalSubtractOperation();
		multiplyOperation = new BigDecimalMultiplyOperation();
		divideOperation = new BigDecimalDivideOperation();
	}

	// Basic addition test
	@Test
	public void testAddOperation() {
		BigDecimal bd1 = new BigDecimal("1000000000000000000000");
		BigDecimal bd2 = new BigDecimal("2000000000000000000000");
		BigDecimal expected = new BigDecimal("3000000000000000000000");
		assertEquals(expected, addOperation.execute(bd1, bd2));
	}

	// Basic subtraction test
	@Test
	public void testSubtractOperation() {
		BigDecimal bd1 = new BigDecimal("1000000000000000000000");
		BigDecimal bd2 = new BigDecimal("2000000000000000000000");
		BigDecimal expected = new BigDecimal("-1000000000000000000000");
		assertEquals(expected, subtractOperation.execute(bd1, bd2));
	}

	// Test for multiplication with large numbers
	@Test
	public void testMultiplyOperation() {
		BigDecimal bd1 = new BigDecimal("1000000000000000000000");
		BigDecimal bd2 = new BigDecimal("2000000000000000000000");
		BigDecimal expected = new BigDecimal("2000000000000000000000000000000000000000");
		assertEquals(-1, expected.compareTo((BigDecimal) multiplyOperation.execute(bd1, bd2)));
	}

	// Test for division with large numbers
	@Test
	public void testDivideOperation() {
		BigDecimal bd1 = new BigDecimal("1000000000000000000000");
		BigDecimal bd2 = new BigDecimal("2000000000000000000000");
		BigDecimal expected = BigDecimal.valueOf(0.5).setScale(BigDecimalDivideOperation.SCALE, RoundingMode.HALF_UP);
		assertEquals(expected, divideOperation.execute(bd1, bd2));
	}

	// Addition with zero
	@Test
	public void testAddWithZero() {
		BigDecimal bd1 = new BigDecimal("1000000000000000000000");
		BigDecimal bd2 = BigDecimal.ZERO;
		BigDecimal expected = new BigDecimal("1000000000000000000000");
		assertEquals(expected, addOperation.execute(bd1, bd2));
	}

	// Subtraction with zero
	@Test
	public void testSubtractWithZero() {
		BigDecimal bd1 = new BigDecimal("1000000000000000000000");
		BigDecimal bd2 = BigDecimal.ZERO;
		BigDecimal expected = new BigDecimal("1000000000000000000000");
		assertEquals(expected, subtractOperation.execute(bd1, bd2));
	}

	// Multiplication with zero
	@Test
	public void testMultiplyWithZero() {
		BigDecimal bd1 = new BigDecimal("1000000000000000000000");
		BigDecimal bd2 = BigDecimal.ZERO;
		BigDecimal expected = BigDecimal.ZERO;
		assertEquals(expected, multiplyOperation.execute(bd1, bd2));
	}

	// Division by zero should throw an ArithmeticException
	@Test
	public void testDivideByZero() {
		BigDecimal bd1 = new BigDecimal("1000000000000000000000");
		BigDecimal bd2 = BigDecimal.ZERO;
		assertThrows(ArithmeticException.class, () -> divideOperation.execute(bd1, bd2));
	}

	// Test with negative numbers for addition
	@Test
	public void testAddWithNegativeNumbers() {
		BigDecimal bd1 = new BigDecimal("-1000000000000000000000");
		BigDecimal bd2 = new BigDecimal("2000000000000000000000");
		BigDecimal expected = new BigDecimal("1000000000000000000000");
		assertEquals(expected, addOperation.execute(bd1, bd2));
	}

	// Test with negative numbers for subtraction
	@Test
	public void testSubtractWithNegativeNumbers() {
		BigDecimal bd1 = new BigDecimal("-1000000000000000000000");
		BigDecimal bd2 = new BigDecimal("2000000000000000000000");
		BigDecimal expected = new BigDecimal("-3000000000000000000000");
		assertEquals(expected, subtractOperation.execute(bd1, bd2));
	}

	// Test for multiplication with rounding
	@Test
	public void testMultiplyWithRounding() {
		BigDecimal bd1 = new BigDecimal("1.2345");
		BigDecimal bd2 = new BigDecimal("1000");
		BigDecimal expected = new BigDecimal("1234.5").setScale(4, RoundingMode.HALF_UP);
		assertEquals(expected, multiplyOperation.execute(bd1, bd2));
	}

	// Test for division with rounding
	@Test
	public void testDivideWithRounding() {
		BigDecimal bd1 = new BigDecimal("10");
		BigDecimal bd2 = new BigDecimal("3");
		BigDecimal expected = new BigDecimal("3.333333333333333").setScale(15, RoundingMode.HALF_UP);
		assertEquals(expected, divideOperation.execute(bd1, bd2));
	}
}
