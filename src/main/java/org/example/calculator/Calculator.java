package org.example.calculator;


import java.util.HashMap;
import java.util.Map;


public class Calculator {
    private final Map<Operation, OperationStrategy> strategies;

    public Calculator(Map<Operation, OperationStrategy> strategies) {
        this.strategies = strategies;
    }

    public double calculate(Operation op, double num1, double num2) {
        OperationStrategy strategy = strategies.get(op);
        if (strategy == null) {
            throw new UnsupportedOperationException("Operation not supported: " + op);
        }
        return strategy.execute(num1, num2);
    }

    public static class Builder {
        private final Map<Operation, OperationStrategy> strategies = new HashMap<>();

        public Builder withOperation(Operation op, OperationStrategy strategy) {
            strategies.put(op, strategy);
            return this;
        }

        public Calculator build() {
            return new Calculator(strategies);
        }
    }

    public ChainedCalculation startChain(double initialValue) {
        return new ChainedCalculation(this, initialValue);
    }

    public static class ChainedCalculation {
        private final Calculator calculator;
        private double currentValue;

        private ChainedCalculation(Calculator calculator, double initialValue) {
            this.calculator = calculator;
            this.currentValue = initialValue;
        }

        public ChainedCalculation apply(Operation op, double num) {
            currentValue = calculator.calculate(op, currentValue, num);
            return this;
        }

        public double getResult() {
            return currentValue;
        }
    }
}