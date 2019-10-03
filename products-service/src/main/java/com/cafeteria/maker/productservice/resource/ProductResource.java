package com.cafeteria.maker.productservice.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.hateoas.ResourceSupport;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductResource extends ResourceSupport {

	@NotNull
	private String name;
	
	@Positive
	private Long productId;
	
}
