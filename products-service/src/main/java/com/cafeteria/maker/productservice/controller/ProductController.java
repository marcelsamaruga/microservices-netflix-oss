package com.cafeteria.maker.productservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.maker.productservice.converter.ProductConverter;
import com.cafeteria.maker.productservice.entity.Product;
import com.cafeteria.maker.productservice.exception.ResourceNotFoundException;
import com.cafeteria.maker.productservice.resource.ProductResource;
import com.cafeteria.maker.productservice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/products")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductConverter converter;
	
	@RequestMapping(path="/findAll", method=RequestMethod.GET)
	public ResponseEntity<List<ProductResource>> findAll(Pageable page) {
		log.info("Entering at ProductController#findAll");
		
		List<ProductResource> resourceList = productService.findAll(page)
												.stream()
												.map(converter::entityToResource)
												.collect(Collectors.toList());
		
		log.info("Exiting ProductController#findAll");

		return new ResponseEntity<List<ProductResource>>(resourceList, HttpStatus.OK);
	}
	
	@PostMapping("save")
	public ResponseEntity<ProductResource> save(@RequestBody @Valid ProductResource productResource) {
		log.info("Entering at ProductController#save");
		
		Product product = converter.resourceToEntity(productResource);
		this.productService.save(product);
		
		log.info("Exiting ProductController#save");

		return new ResponseEntity<ProductResource>(productResource, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResource> findById(@PathVariable Long id) throws ResourceNotFoundException {
		log.info("Entering at ProductController#findById");
		
		if (id == null) {
			throw new IllegalArgumentException("Invalid parameter");
		}
		
		final ProductResource productResource = converter.entityToResource(productService.findById(id));
		
		log.info("Exiting ProductController#findById");
		
		return new ResponseEntity<>(productResource, HttpStatus.OK);
	}

}
