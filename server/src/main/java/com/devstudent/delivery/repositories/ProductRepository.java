package com.devstudent.delivery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devstudent.delivery.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findAllByOrderByNameAsc();
}
