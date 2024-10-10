package com.brunobasques_jsp.fifth_challange.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brunobasques_jsp.fifth_challange.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT obj FROM Order obj JOIN FETCH obj.items WHERE obj.id = :id")
	Optional<Order> findById(Long id);
}
