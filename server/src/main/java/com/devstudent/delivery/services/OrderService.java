package com.devstudent.delivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devstudent.delivery.entities.Order;
import com.devstudent.delivery.entities.OrderStatus;
import com.devstudent.delivery.entities.Product;
import com.devstudent.delivery.repositories.OrderRepository;
import com.devstudent.delivery.repositories.ProductRepository;
import com.devstudent.delivery.services.dto.OrderDTO;
import com.devstudent.delivery.services.dto.ProductDTO;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	@Transactional()
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), dto.getMoment(),
				OrderStatus.PENDING);

		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}

		order = repository.save(order);

		return new OrderDTO(order);
	}
}
