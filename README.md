# Flexible Calculator

## Overview
This project implements a flexible, extensible calculator in Java. It supports basic arithmetic operations and allows for easy addition of new operations. The calculator is designed with object-oriented principles in mind, particularly adhering to the Open-Closed Principle.

## Features
- Basic arithmetic operations (ADD, SUBTRACT, MULTIPLY, DIVIDE)
- Extensible design allowing easy addition of new operations
- Chaining of multiple operations
- Error handling for invalid operations and division by zero

## Project Structure
```
src/
├── main/java/org/example/
│   ├── Calculator.java
│   ├── Operation.java
│   └── OperationStrategy.java
└── test/java/org/example/
    └── CalculatorTest.java
pom.xml
README.md
```

## Setup
1. Ensure you have Java 19 and Maven installed on your system.
2. Clone this repository:
   ```
   git clone [repository-url]
   ```
3. Navigate to the project directory:
   ```
   cd Calc
   ```
4. Build the project:
   ```
   mvn clean install
   ```

## Usage
Here's a basic example of how to use the calculator:

```java
Calculator calculator = new Calculator.Builder()
    .withOperation(Operation.ADD, (a, b) -> a + b)
    .withOperation(Operation.SUBTRACT, (a, b) -> a - b)
    .withOperation(Operation.MULTIPLY, (a, b) -> a * b)
    .withOperation(Operation.DIVIDE, (a, b) -> a / b)
    .build();

// Perform a simple calculation
double result = calculator.calculate(Operation.ADD, 5, 3);
System.out.println(result); // Outputs: 8.0

// Chain multiple operations
double chainedResult = calculator.startChain(10)
    .apply(Operation.ADD, 5)
    .apply(Operation.MULTIPLY, 2)
    .apply(Operation.SUBTRACT, 7)
    .apply(Operation.DIVIDE, 3)
    .getResult();
System.out.println(chainedResult); // Outputs: 7.666666666666667
```

## Adding New Operations
To add a new operation:

1. Add the new operation to the `Operation` enum.
2. When building the calculator, include the new operation with its implementation:

```java
Calculator calculator = new Calculator.Builder()
    // ... other operations ...
    .withOperation(Operation.NEW_OPERATION, (a, b) -> /* implementation */)
    .build();
```

## Running Tests
To run the tests, use the following Maven command:
```
mvn test
```

## Design Decisions and Assumptions

1. **Strategy Pattern**: We used the Strategy pattern to implement operations. This allows for easy addition of new operations without modifying existing code, adhering to the Open-Closed Principle.

2. **Builder Pattern**: The Calculator class uses the Builder pattern. This provides a flexible way to construct Calculator instances with different sets of operations.

3. **Immutability**: The Calculator instance is immutable once created. This ensures thread-safety and prevents unexpected changes to the calculator's behavior after initialization.

4. **Chaining Operations**: We implemented a fluent interface for chaining operations. This provides a more intuitive and readable way to perform complex calculations.

5. **Error Handling**: We assumed that division by zero should throw an ArithmeticException. Other invalid operations (e.g., using an operation not supported by the calculator) throw an UnsupportedOperationException.

6. **Double Precision**: We used double for all calculations. This assumes that the level of precision provided by double is sufficient for the calculator's use cases. For applications requiring higher precision, BigDecimal might be a better choice.

7. **Enum for Operations**: We used an enum to represent operations. This provides type safety and makes it easier to add new operations in a structured way.

8. **IoC Compatibility**: The design is compatible with Inversion of Control containers, making it easy to use in larger applications that use dependency injection.

9. **Testing**: We assumed that thorough unit testing is crucial. The CalculatorTest class includes tests for basic operations, chained operations, and error cases.

These design decisions aim to create a flexible, maintainable, and extensible calculator that can be easily adapted to various use cases and expanded with new functionality as needed.

