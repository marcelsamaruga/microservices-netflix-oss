package com.cafeteria.maker.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafeteria.maker.userservice.resource.UserResource;

@RequestMapping("/user")
public class UserController {

	@GetMapping("/{id}")
	public ResponseEntity<UserResource> findById(@PathVariable Long id) {
		return null;
		
	}
	
}
