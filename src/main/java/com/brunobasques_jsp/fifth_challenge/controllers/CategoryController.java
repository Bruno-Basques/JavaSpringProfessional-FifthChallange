package com.brunobasques_jsp.fifth_challenge.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunobasques_jsp.fifth_challenge.dtos.CategoryDTO;
import com.brunobasques_jsp.fifth_challenge.entities.Category;
import com.brunobasques_jsp.fifth_challenge.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> entityList = service.findAll();
        List<CategoryDTO> dtoList = entityList
        		.stream()
        		.map(x -> modelMapper.map(x, CategoryDTO.class))
        		.toList();
        return ResponseEntity.ok(dtoList);
    }
}