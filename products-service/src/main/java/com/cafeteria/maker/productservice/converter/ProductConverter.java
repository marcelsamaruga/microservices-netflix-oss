package com.cafeteria.maker.productservice.converter;

import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import com.cafeteria.maker.productservice.controller.ProductController;
import com.cafeteria.maker.productservice.entity.Product;
import com.cafeteria.maker.productservice.exception.ResourceNotFoundException;
import com.cafeteria.maker.productservice.resource.ProductResource;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductConverter {
	
	public Product resourceToEntity(ProductResource resource) {
		return Product.builder()
				.id(resource.getProductId())
				.name(resource.getName())
				.build();
	}
	
	public ProductResource entityToResource(Product product) {
		ProductResource resource = ProductResource.builder()
				.productId(product.getId())
				.name(product.getName())
				.build();

	    resource.add(linkTo(ProductController.class).withRel("products"));
	    try {
			resource.add(linkTo(methodOn(ProductController.class).findById(product.getId())).withRel("products"));
		} catch (ResourceNotFoundException e) {
			log.error("Error creating the link for findById");
		}

		return resource;
	}

}
