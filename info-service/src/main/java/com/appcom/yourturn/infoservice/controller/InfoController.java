package com.appcom.yourturn.infoservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InfoController {
	
	@Value("${application.name}")
	private String applicationName;
	
	@Value("${application.author.name}")
	private String author;
	
	@Value("${application.author.email}")
	private String email;
	
	@Value("${application.version}")
	private String version;

	@GetMapping("/info")
	public ResponseEntity<String> getInfo() {
		log.info("Entering at InfoController#getInfo");
		
		StringBuilder sb = new StringBuilder();
		sb.append("Information Service\n")
		  .append("-----------------------------\n")
		  .append("Application: ").append(this.applicationName).append("\n")
		  .append("- Author: ").append(this.author).append("\n")
		  .append("- Contact: ").append(this.email).append("\n")
		  .append("------------------------------\n")
		  .append("Version: ").append(this.version);
		
		log.info("Exiting InfoController#getInfo");
		return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
	}
	
}
