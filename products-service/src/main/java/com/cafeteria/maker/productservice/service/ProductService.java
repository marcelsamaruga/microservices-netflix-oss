package com.cafeteria.maker.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cafeteria.maker.productservice.entity.Product;
import com.cafeteria.maker.productservice.exception.ResourceNotFoundException;
import com.cafeteria.maker.productservice.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return this.productRepository.save(product);
	}

	public Page<Product> findAll(Pageable page) {
		return this.productRepository.findAll(page);
	}
	
	public Product findById(Long id) throws ResourceNotFoundException {
		return this.productRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Argument not found.")
				);
	}
	
	public List<Product> findByName(String name, Pageable page) throws ResourceNotFoundException {
		return productRepository.findByName(name, page).orElseThrow(
					() -> new ResourceNotFoundException("The following name has not found: " + name)
				);
	}

}
