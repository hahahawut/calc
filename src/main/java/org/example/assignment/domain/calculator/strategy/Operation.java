package org.example.assignment.domain.calculator.strategy;

public interface Operation {
	Number execute(Number a, Number b);
}