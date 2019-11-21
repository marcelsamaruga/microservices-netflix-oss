package com.comapp.yourturn.schedulingservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.comapp.yourturn.schedulingservice.entity.Scheduling;
import com.comapp.yourturn.schedulingservice.exception.ResourceNotFoundException;
import com.comapp.yourturn.schedulingservice.remoteclient.ProductClient;
import com.comapp.yourturn.schedulingservice.remoteclient.UserClient;
import com.comapp.yourturn.schedulingservice.repository.SchedulingRepository;
import com.comapp.yourturn.schedulingservice.resource.ProductResource;
import com.comapp.yourturn.schedulingservice.resource.UserResource;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SchedulingService {
	
	private final SchedulingRepository repository;
	private final ProductClient productClient;
	private final UserClient userClient;
	
	public Scheduling save(Scheduling scheduling) throws ResourceNotFoundException {
		ProductResource productResource = getProduct(scheduling.getProductGuid()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		scheduling.setProductGuid(productResource.getGuid());
		
		UserResource userResource = getUser(scheduling.getUserGuid()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		scheduling.setUserGuid(userResource.getGuid());
		
		return repository.save(scheduling);
	}
	
	public List<Scheduling> getScheduleByDate(String dateString) throws ParseException, ResourceNotFoundException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse(dateString);
		
		return repository.findSchedulingByDate(date).orElseThrow(
				() -> new ResourceNotFoundException("No scheduling has found for " + sdf.format(date))
			);
	}
	
	@HystrixCommand(fallbackMethod="optionalNullable", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
	})
	private Optional<ProductResource> getProduct(String guid) {
		return Optional.of(productClient.findByGuid(guid));
	}
	
	@HystrixCommand(fallbackMethod="optionalNullable", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
	})
	private Optional<UserResource> getUser(String guid) {
		return Optional.of(userClient.findByGuid(guid));
	}
	
	private Optional<?> optinalNullable() {
		return Optional.ofNullable(null);
	}

}
