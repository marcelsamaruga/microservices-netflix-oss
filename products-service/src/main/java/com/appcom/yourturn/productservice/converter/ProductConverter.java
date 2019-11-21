package com.appcom.yourturn.productservice.converter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.stereotype.Component;

import com.appcom.yourturn.productservice.controller.ProductController;
import com.appcom.yourturn.productservice.entity.Product;
import com.appcom.yourturn.productservice.exception.ResourceNotFoundException;
import com.appcom.yourturn.productservice.helper.ProductHelper;
import com.appcom.yourturn.productservice.resource.ProductResource;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class ProductConverter {
	
	private final ProductHelper productHelper;
	
	public Product resourceToEntity(ProductResource resource) {
		return Product.builder()
				.id(resource.getProductId())
				.name(resource.getName())
				.guid(productHelper.getGuid(resource.getGuid()))
				.build();
	}
	
	public ProductResource entityToResource(Product product) {
		ProductResource resource = ProductResource.builder()
				.productId(product.getId())
				.name(product.getName())
				.guid(productHelper.getGuid(product.getGuid()))
				.build();

	    try {
			resource.add(linkTo(
					methodOn(ProductController.class).findById(product.getId()))
					.withSelfRel()
			);
			
			resource.add(linkTo(
					methodOn(ProductController.class).findByGuid(product.getGuid()))
					.withSelfRel());
			
		} catch (ResourceNotFoundException ex) {
			log.error(ex.getMessage());
		}
	    
		return resource;
	}
}
