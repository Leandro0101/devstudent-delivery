package com.devstudent.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devstudent.delivery.entities.Product;

public interface OrderRepository extends JpaRepository<Product, Long> {

}
