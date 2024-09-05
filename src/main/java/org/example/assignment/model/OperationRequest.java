package org.example.assignment.model;

import lombok.*;

@Data
@Getter
@Builder
@Setter
@AllArgsConstructor
public class OperationRequest {
	private String op;
	private Number num;
}
