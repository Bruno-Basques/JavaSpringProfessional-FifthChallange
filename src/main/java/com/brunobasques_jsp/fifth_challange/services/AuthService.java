package com.brunobasques_jsp.fifth_challange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brunobasques_jsp.fifth_challange.entities.User;
import com.brunobasques_jsp.fifth_challange.services.exceptions.ForbiddenException;

@Service
public class AuthService {

	@Autowired
	private UserService userService;
	
	public void validateSelfOrAdmin(long userId) {
		User loggedUser = userService.authenticated();
		if (!loggedUser.hasRole("ROLE_ADMIN") && !loggedUser.getId().equals(userId)) {
			throw new ForbiddenException("Access denied");
		}
	}
}
