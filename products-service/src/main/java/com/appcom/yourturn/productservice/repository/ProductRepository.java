package com.appcom.yourturn.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.appcom.yourturn.productservice.entity.Product;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<List<Product>> findByName(String name, Pageable page);
	Optional<Product> findByGuid(String guid);
}
