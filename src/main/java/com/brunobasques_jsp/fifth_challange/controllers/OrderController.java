package com.brunobasques_jsp.fifth_challange.controllers;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brunobasques_jsp.fifth_challange.dtos.OrderDTO;
import com.brunobasques_jsp.fifth_challange.entities.Order;
import com.brunobasques_jsp.fifth_challange.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;
    
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
        Order entity = service.findById(id);
        OrderDTO dto = modelMapper.map(entity, OrderDTO.class);
        
        return ResponseEntity.ok(dto);
    }
    
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) {
        Order entity = service.insert(modelMapper.map(dto, Order.class));
        dto = modelMapper.map(entity, OrderDTO.class);
        URI uri = ServletUriComponentsBuilder
        		.fromCurrentRequest()
        		.path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        
        return ResponseEntity.created(uri).body(dto);
    } 
}
