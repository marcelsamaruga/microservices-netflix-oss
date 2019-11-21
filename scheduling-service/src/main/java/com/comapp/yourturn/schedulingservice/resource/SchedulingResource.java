package com.comapp.yourturn.schedulingservice.resource;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchedulingResource extends ResourceSupport {

	@JsonIgnore
	private Long schedulingId;
	
	@NotNull
	private String date;

	@NotNull
	private UserResource user;
	
	@NotNull
	private ProductResource product;
}
