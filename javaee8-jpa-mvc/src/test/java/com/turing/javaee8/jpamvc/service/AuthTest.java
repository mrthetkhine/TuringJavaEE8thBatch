package com.turing.javaee8.jpamvc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.turing.javaee8.jpamvc.model.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AuthTest {
	
	@Autowired
	AuthService authService;
	
	@Test
	void testAddUser()
	{
		UserDto user = new UserDto();
		user.setUsername("user");
		user.setPassword("user");
		try {
			authService.register(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
