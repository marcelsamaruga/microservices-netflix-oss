package com.comapp.yourturn.schedulingservice.controller;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comapp.yourturn.schedulingservice.converter.SchedulingConverter;
import com.comapp.yourturn.schedulingservice.entity.Scheduling;
import com.comapp.yourturn.schedulingservice.exception.ResourceNotFoundException;
import com.comapp.yourturn.schedulingservice.resource.SchedulingResource;
import com.comapp.yourturn.schedulingservice.service.SchedulingService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/schedule")
@Slf4j
@AllArgsConstructor
public class SchedulingController {

	private final SchedulingService schedulingService;
	private final SchedulingConverter schedulingConverter;
	
	@PostMapping
	public ResponseEntity<SchedulingResource> save(@Valid @RequestBody SchedulingResource resource) throws ResourceNotFoundException, ParseException {
		log.info("Entering at SchedulingController#save");
		
		Scheduling scheduling = schedulingService.save(
										schedulingConverter.toEntity(resource)
									);
		
		SchedulingResource schedulingResource = schedulingConverter.toResource(scheduling);
		
		log.info("Exiting SchedulingController#save");
		
		return ResponseEntity.ok(schedulingResource);
	}
	
	@GetMapping
	public ResponseEntity<List<SchedulingResource>> getScheduleByDate(@RequestParam(required=true) String date) throws ParseException, ResourceNotFoundException {
		log.info("Entering at SchedulingController#getScheduleByDate");
		
		List<SchedulingResource> resourceList = this.schedulingService.getScheduleByDate(date)
													.stream()
													.map(schedulingConverter::toResource)
													.collect(Collectors.toList());
		
		log.info("Exiting =SchedulingController#getScheduleByDate");
		
		return ResponseEntity.ok(resourceList);
	}
	
}
