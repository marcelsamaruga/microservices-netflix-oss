package com.appcom.yourturn.productservice.helper;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ProductHelper {

	public String getGuid(String guid) {
		if (StringUtils.isEmpty(guid)) {
			guid = UUID.randomUUID().toString();
		}
		
		return guid;
	}
	
}
