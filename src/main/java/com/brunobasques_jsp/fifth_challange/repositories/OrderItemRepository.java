package com.brunobasques_jsp.fifth_challange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunobasques_jsp.fifth_challange.entities.OrderItem;
import com.brunobasques_jsp.fifth_challange.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
