package com.comapp.yourturn.schedulingservice.resource;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResource {

	@NotNull
	private String guid;
	
	@JsonInclude(Include.NON_NULL)
	private String name;
	
}
