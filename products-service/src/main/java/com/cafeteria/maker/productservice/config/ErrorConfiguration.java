package com.cafeteria.maker.productservice.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.maker.productservice.exception.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class ErrorConfiguration {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String errorHandling(ResourceNotFoundException exception) {
		return exception.getMessage();
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String errorHandling(IllegalArgumentException exception) {
		return exception.getMessage();
	}
}
