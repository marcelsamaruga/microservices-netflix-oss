package com.appcom.yourturn.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.appcom.yourturn.productservice.entity.Product;
import com.appcom.yourturn.productservice.exception.ResourceNotFoundException;
import com.appcom.yourturn.productservice.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product save(Product product) {
		return this.productRepository.save(product);
	}
	
	public void delete(Long id) {
		this.productRepository.deleteById(id);
	}

	public Page<Product> findAll(Pageable page) {
		return this.productRepository.findAll(page);
	}
	
	public Product findById(Long id) throws ResourceNotFoundException {
		return this.productRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Argument not found.")
				);
	}
	
	public Product findByGuid(String guid) throws ResourceNotFoundException {
		return this.productRepository.findByGuid(guid).orElseThrow(
					() -> new ResourceNotFoundException("Argument not found.")
				);
	}
	
	public List<Product> findByName(String name, Pageable page) throws ResourceNotFoundException {
		return productRepository.findByName(name, page).orElseThrow(
					() -> new ResourceNotFoundException("The following name has not found: " + name)
				);
	}

}
