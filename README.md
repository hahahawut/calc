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

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
[Specify your license here]
