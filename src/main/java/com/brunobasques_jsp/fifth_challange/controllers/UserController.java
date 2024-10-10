package com.brunobasques_jsp.fifth_challange.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunobasques_jsp.fifth_challange.dtos.UserDTO;
import com.brunobasques_jsp.fifth_challange.entities.User;
import com.brunobasques_jsp.fifth_challange.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;
    
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe() {
    	User entity = service.getLoggedUser();
    	UserDTO dto = modelMapper.map(entity, UserDTO.class);
        return ResponseEntity.ok(dto);
    }
}
