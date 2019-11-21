package com.cafeteria.maker.userservice.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResource extends RepresentationModel<UserResource> {

	@Positive
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
}
