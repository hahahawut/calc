package org.example;


import org.example.calculator.Calculator;
import org.example.calculator.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator.Builder()
                .withOperation(Operation.ADD, (a, b) -> a + b)
                .withOperation(Operation.SUBTRACT, (a, b) -> a - b)
                .withOperation(Operation.MULTIPLY, (a, b) -> a * b)
                .withOperation(Operation.DIVIDE, (a, b) -> {
                    if (b == 0) throw new ArithmeticException("Division by zero");
                    return a / b;
                })
                .build();
    }

    @Test
    void testBasicCalculations() {
        assertEquals(5, calculator.calculate(Operation.ADD, 2, 3), 0.001);
        assertEquals(-1, calculator.calculate(Operation.SUBTRACT, 2, 3), 0.001);
        assertEquals(6, calculator.calculate(Operation.MULTIPLY, 2, 3), 0.001);
        assertEquals(2, calculator.calculate(Operation.DIVIDE, 6, 3), 0.001);
    }

    @Test
    void testChainedCalculations() {
        double result = calculator.startChain(10)
                .apply(Operation.ADD, 5)
                .apply(Operation.MULTIPLY, 2)
                .apply(Operation.SUBTRACT, 7)
                .apply(Operation.DIVIDE, 3)
                .getResult();
        assertEquals(7.666666666666667, result, 0.000001);
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.calculate(Operation.DIVIDE, 5, 0));
    }

    @Test
    void testUnsupportedOperation() {
        Calculator incompleteCalculator = new Calculator.Builder()
                .withOperation(Operation.ADD, (a, b) -> a + b)
                .build();
        assertThrows(UnsupportedOperationException.class,
                () -> incompleteCalculator.calculate(Operation.SUBTRACT, 5, 3));
    }
}