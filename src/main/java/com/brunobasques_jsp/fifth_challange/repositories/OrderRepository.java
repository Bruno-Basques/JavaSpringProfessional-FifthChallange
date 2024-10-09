package com.brunobasques_jsp.fifth_challange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunobasques_jsp.fifth_challange.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
