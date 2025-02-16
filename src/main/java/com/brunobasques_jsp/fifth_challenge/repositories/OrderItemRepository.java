package com.brunobasques_jsp.fifth_challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunobasques_jsp.fifth_challenge.entities.OrderItem;
import com.brunobasques_jsp.fifth_challenge.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
