package com.cafeteria.maker.userservice.converter;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.cafeteria.maker.userservice.controller.UserController;
import com.cafeteria.maker.userservice.entity.User;
import com.cafeteria.maker.userservice.resource.UserResource;

@Component
public class UserConverter extends RepresentationModelAssemblerSupport<User, UserResource> {
	
	public UserConverter() {
		super(UserController.class, UserResource.class);
	}

	@Override
	public UserResource toModel(User entity) {
		UserResource resource = UserResource.builder()
												.id(entity.getId())
												.name(entity.getName())
												.email(entity.getEmail())
												.build();
		
		resource.add(new Link("/team/{teamId}").withRel(LinkRelation.of("team")).expand(entity.getTeam().getId()));

		return resource;
	}

	
}
