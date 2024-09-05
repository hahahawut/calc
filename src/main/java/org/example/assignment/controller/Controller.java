package org.example.assignment.controller;

import org.example.assignment.model.CalculateRequest;
import org.example.assignment.model.ChainRequest;
import org.example.assignment.model.Response;
import org.example.assignment.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/calc")
public class Controller {

	private final CalculationService calculationService;

	@Autowired
	public Controller(CalculationService calculationService) {
		this.calculationService = calculationService;
	}

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello, World!";
	}

	@PostMapping("/calculate")
	public Response calculate(@RequestBody CalculateRequest calculateRequest) {
		Number result = calculationService.calculate(calculateRequest);
		return Response.builder().code("200").message("Calculation successful").data(result).build();
	}

	@PostMapping("/chain")
	public Response chainOperations(@RequestBody ChainRequest chainRequest) {
		Number result = calculationService.calculateChain(chainRequest);
		return Response.builder().code("200").message("Chained calculation successful").data(result).build();
	}
}