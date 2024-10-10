package com.brunobasques_jsp.fifth_challange.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunobasques_jsp.fifth_challange.entities.Order;
import com.brunobasques_jsp.fifth_challange.entities.OrderItem;
import com.brunobasques_jsp.fifth_challange.entities.Product;
import com.brunobasques_jsp.fifth_challange.entities.User;
import com.brunobasques_jsp.fifth_challange.entities.enumerators.OrderStatus;
import com.brunobasques_jsp.fifth_challange.services.exceptions.ResourceNotFoundException;
import com.brunobasques_jsp.fifth_challange.repositories.OrderItemRepository;
import com.brunobasques_jsp.fifth_challange.repositories.OrderRepository;
import com.brunobasques_jsp.fifth_challange.repositories.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return order;
    }

    @Transactional
	public Order insert(Order order) {
		
    	Order newlySavedOrder = new Order();
    	
    	newlySavedOrder.setMoment(Instant.now());
    	newlySavedOrder.setStatus(OrderStatus.WAITING_PAYMENT);
    	
    	User user = userService.authenticated();
    	newlySavedOrder.setClient(user);
    	
    	for (OrderItem item : order.getItems()) {
    		Product product = productRepository.getReferenceById(item.getProduct().getId());
    		OrderItem newlySavedOrderItem = new OrderItem(newlySavedOrder, product, item.getQuantity(), product.getPrice());
    		newlySavedOrder.getItems().add(newlySavedOrderItem);
    	}
    	
    	repository.save(newlySavedOrder);
    	orderItemRepository.saveAll(newlySavedOrder.getItems());
    	
    	return newlySavedOrder;
	}
}
