package org.example.assignment.controller;

import org.example.assignment.model.CalculateRequest;
import org.example.assignment.model.ChainRequest;
import org.example.assignment.model.Response;
import org.example.assignment.service.CalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/calc")
public class CalculatorController {

	private final CalculationService calculationService;

	// Using constructor injection, so @Autowired is not required on constructors in recent Spring versions.
	public CalculatorController(CalculationService calculationService) {
		this.calculationService = calculationService;
	}

	@GetMapping("/hello")
	public ResponseEntity<String> helloWorld() {
		return ResponseEntity.ok("Hello World!");
	}

	@PostMapping("/calculate")
	public ResponseEntity<Response> calculate(@RequestBody CalculateRequest calculateRequest) {
		Number result = calculationService.calculate(calculateRequest);
		Response response = Response.builder()
				.code(HttpStatus.OK.toString())
				.message("Calculation successful")
				.data(result)
				.build();
		return ResponseEntity.ok(response);
	}

	@PostMapping("/chain")
	public ResponseEntity<Response> chainOperations(@RequestBody ChainRequest chainRequest) {
		Number result = calculationService.calculateChain(chainRequest);
		Response response = Response.builder()
				.code(HttpStatus.OK.toString())
				.message("Chained calculation successful")
				.data(result)
				.build();
		return ResponseEntity.ok(response);
	}
}
