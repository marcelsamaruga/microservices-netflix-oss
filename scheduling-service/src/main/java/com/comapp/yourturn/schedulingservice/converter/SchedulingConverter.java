package com.comapp.yourturn.schedulingservice.converter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.comapp.yourturn.schedulingservice.controller.SchedulingController;
import com.comapp.yourturn.schedulingservice.entity.Scheduling;
import com.comapp.yourturn.schedulingservice.resource.ProductResource;
import com.comapp.yourturn.schedulingservice.resource.SchedulingResource;
import com.comapp.yourturn.schedulingservice.resource.UserResource;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulingConverter extends ResourceAssemblerSupport<Scheduling, SchedulingResource> {
	
	@Qualifier("formatDate")
	@Autowired
	private SimpleDateFormat sdf;

	public SchedulingConverter() {
		super(SchedulingController.class, SchedulingResource.class);
	}

	public Scheduling toEntity(SchedulingResource resource) throws ParseException {
		return Scheduling.builder()
						 .id(resource.getSchedulingId())
						 .date(sdf.parse(resource.getDate()))
						 .productGuid(resource.getProduct().getGuid())
						 .userGuid(resource.getUser().getGuid())
						 .build();
	}
	
	@Override
	public SchedulingResource toResource(Scheduling entity) {
		SchedulingResource schedulingResource = 
				SchedulingResource.builder()
								  .schedulingId(entity.getId())
								  .product( ProductResource.builder().guid(entity.getProductGuid()).build() )
								  .user( UserResource.builder().guid(entity.getUserGuid()).build() )
								  .date(sdf.format(entity.getDate()))
								  .build();
		
		try { 
			if (entity.getDate() != null) {
				Link selfLink = linkTo(methodOn(SchedulingController.class).getScheduleByDate(sdf.format(entity.getDate()))).withSelfRel();
				schedulingResource.add(selfLink);
			}
		} catch (Exception e) {
			log.error("An error happend creating the links at TeamConverter#entityToResource. Error: " + e.getMessage()); 
		}
		
		
		return schedulingResource;
	}
	
	@Bean("formatDate")
	private SimpleDateFormat sdfFormatter() {
		return new SimpleDateFormat("dd/MM/yyyy");
	}
}