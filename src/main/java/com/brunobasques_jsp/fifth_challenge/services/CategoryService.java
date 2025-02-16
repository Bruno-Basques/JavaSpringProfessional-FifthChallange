package com.brunobasques_jsp.fifth_challenge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brunobasques_jsp.fifth_challenge.entities.Category;
import com.brunobasques_jsp.fifth_challenge.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        List<Category> result = repository.findAll();
        return result;
    }
}
