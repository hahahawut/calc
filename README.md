# Flexible Calculator

## Overview
This project implements a flexible, extensible calculator in Java. It supports basic arithmetic operations and allows for easy addition of new operations. The calculator is designed with object-oriented principles in mind, particularly adhering to the Open-Closed Principle.

## Features
- Basic arithmetic operations [ADD, SUBTRACT, MULTIPLY, DIVIDE]
- Extensible design allowing easy addition of new operations
- Chaining of multiple operations
- Error handling for invalid operations and division by zero

## Project Structure
```
src/
├── main/java/org/example/assignment/
│   ├── controller/                # Handles incoming requests and responses
│   │   └── Controller.java
│   ├── domain/calculator/          # Business logic for calculation
│   │   ├── strategy/               # Strategy pattern for handling different operations
│   │   │   ├── Calculator.java     # Main Calculator class
│   │   │   └── ChainedCalculator.java # Handles chaining of multiple operations
│   ├── exception/                  # Handles errors and exceptions
│   │   ├── ComputationException.java
│   │   ├── ExceptionHandler.java
│   │   └── InvalidParameterException.java
│   ├── model/                      # Models representing data transfer objects (DTOs)
│   │   ├── CalculateRequest.java
│   │   ├── ChainRequest.java
│   │   ├── MathOperation.java      # Enum representing available math operations
│   │   ├── OperationRequest.java
│   │   └── Response.java
│   ├── service/                    # Services implementing business logic
│   │   ├── CalculationService.java
│   │   └── InputValidationService.java
│   └── Application.java            # Entry point for the application
│
├── resources/                      # Configuration files
│   ├── application.properties      # Spring Boot configuration
│   ├── JSONEventLayoutV1.json      # JSON layout for logging
│   └── log4j2.xml                  # Log4j2 configuration for logging
│
├── test/java/org/example/assignment/domain/calculator/strategy/
│   ├── BigDecimalMathOperationTest.java  # Test for BigDecimal operations
│   ├── DoubleMathOperationTest.java      # Test for double operations
│   └── IntegerMathOperationTest.java     # Test for integer operations

```

## Setup
1. Ensure you have Java 19 and Maven installed on your system.
2. Clone this repository:
   ```
   git clone git@github.com:nergui/calc.git
   ```
3. Navigate to the project directory:
   ```
   cd calc
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


## Sample API Usage

1. Basic Addition

```
curl --location 'localhost:9000/v1/calc' \
--header 'Content-Type: application/json' \
--data '{
    "op": "ADD",
    "num1": 1,
    "num2": 2
}'

```
2. Chain Operations

```
curl --location 'localhost:9000/v1/calc' \
--header 'Content-Type: application/json' \
--data '{
    "operations": [
        {"op": "ADD", "num": 5},
        {"op": "SUBTRACT", "num": 2},
        {"op": "MULTIPLY", "num": 3},
        {"op": "DIVIDE", "num": 4}
    ]
}'


```