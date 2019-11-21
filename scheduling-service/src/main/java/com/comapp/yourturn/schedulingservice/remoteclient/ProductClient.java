package com.comapp.yourturn.schedulingservice.remoteclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comapp.yourturn.schedulingservice.resource.ProductResource;

@FeignClient("product-service")
public interface ProductClient {

	@RequestMapping(method = RequestMethod.GET, value = "/products/guid/{guid}")
	public ProductResource findByGuid(@PathVariable(value="guid") String guid);
	
}
