package com.comapp.yourturn.schedulingservice.remoteclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comapp.yourturn.schedulingservice.resource.UserResource;

@FeignClient("user-service")
public interface UserClient {

	@RequestMapping(method = RequestMethod.GET, value = "/user/guid/{guid}")
	public UserResource findByGuid(@PathVariable(value="guid") String guid);
	
}
