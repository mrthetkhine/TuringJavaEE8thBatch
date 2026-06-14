package com.turing.javaee8.webfluxmongo.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.webfluxmongo.model.Role;
import com.turing.javaee8.webfluxmongo.model.User;
import com.turing.javaee8.webfluxmongo.operator.Delay;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Rollback(false)
@SpringBootTest
//@DataMongoTest
@AutoConfigureDataMongo
public class AuthServiceTest {
	
	@Autowired
	AuthService authService;
	
	@Test
	public void testRegister()
	{
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@gmail.com");
		user.setPassword("admin");
		
		List<Role> roles = new ArrayList<>();
		
		Role role = new Role();
		role.setRole("ROLE_ADMIN");
		
		roles.add(role);
		user.setRoles(roles);
		
		this.authService
			.register(user)
			.subscribe(data->{
				System.out.println("Data "+data);
			});
			
						
		Delay.delay(3000);
	}
}
