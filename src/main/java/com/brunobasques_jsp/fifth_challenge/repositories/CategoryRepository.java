package com.brunobasques_jsp.fifth_challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunobasques_jsp.fifth_challenge.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
