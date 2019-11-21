package com.appcom.yourturn.productservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appcom.yourturn.productservice.converter.ProductConverter;
import com.appcom.yourturn.productservice.entity.Product;
import com.appcom.yourturn.productservice.exception.ResourceNotFoundException;
import com.appcom.yourturn.productservice.resource.ProductResource;
import com.appcom.yourturn.productservice.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/products")
@Slf4j
@AllArgsConstructor
public class ProductController {
	
	private static final String INVALID_PARAM = "The method requires a valid parameter";
	private final ProductService productService;
	private final ProductConverter converter;
	
	@RequestMapping(path="/findAll", method=RequestMethod.GET)
	public ResponseEntity<List<ProductResource>> findAll(Pageable page) {
		log.info("Entering at ProductController#findAll");
		
		List<ProductResource> resourceList = productService.findAll(page)
												.stream()
												.map(converter::entityToResource)
												.collect(Collectors.toList());
		
		log.info("Exiting ProductController#findAll");

		return ResponseEntity.ok(resourceList);
	}
	
	@PostMapping("save")
	public ResponseEntity<ProductResource> save(@RequestBody @Valid ProductResource productResource) {
		log.info("Entering at ProductController#save");
		
		Product product = this.productService.save(converter.resourceToEntity(productResource));
		
		ProductResource resource = converter.entityToResource(product);
		
		log.info("Exiting ProductController#save");

		return ResponseEntity.ok(resource);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ProductResource> delete(@PathVariable String id) {
		log.info("Entering at ProductController#delete");
		
		if (StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException(INVALID_PARAM);
		}
		
		this.productService.delete(Long.valueOf(id));
		
		log.info("Exiting ProductController#delete");

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResource> findById(@PathVariable Long id) throws ResourceNotFoundException {
		log.info("Entering at ProductController#findById");
		
		final ProductResource productResource = converter.entityToResource(productService.findById(id));
		
		log.info("Exiting ProductController#findById");
		
		return ResponseEntity.ok(productResource);
	}
	
	@GetMapping("/guid/{guid}")
	public ResponseEntity<ProductResource> findByGuid(@PathVariable String guid) throws ResourceNotFoundException {
		log.info("Entering at ProductController#findByGuid");
		
		final ProductResource productResource = converter.entityToResource(productService.findByGuid(guid));
		
		log.info("Exiting ProductController#findById");
		
		return ResponseEntity.ok(productResource);
	}

}
