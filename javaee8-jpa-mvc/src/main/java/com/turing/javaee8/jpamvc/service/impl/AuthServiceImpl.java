package com.turing.javaee8.jpamvc.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.turing.javaee8.jpamvc.model.Role;
import com.turing.javaee8.jpamvc.model.User;
import com.turing.javaee8.jpamvc.model.dto.UserDto;
import com.turing.javaee8.jpamvc.repository.UserDao;
import com.turing.javaee8.jpamvc.security.JwtService;
import com.turing.javaee8.jpamvc.security.SecurityUtil;
import com.turing.javaee8.jpamvc.service.AuthService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	SecurityUtil securityUtil;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
    JwtService jwtService;
  

	    
	@Transactional
	@Override
	public void register(UserDto userDto) throws Exception {
		User existingUser = this.userDao.findByUsername(userDto.getUsername());
		if(existingUser!=null)
		{
			throw new Exception("user already existed");
		}
		else
		{
			User user = new User();
			user.setUsername(userDto.getUsername());
			user.setPassword(securityUtil.getHash(userDto.getPassword()));
			
			Role role1 = new Role();
			role1.setRole("ROLE_USER");
			user.getRoles().add(role1);
			
			this.userDao.save(user);
			
			userDto.setPassword(user.getPassword());
			//return userDto;
		}
	}

	@Override
	public String login(UserDto userDto) {
		log.info("Login attempt "+userDto.getUsername());
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
		log.info("Login ok "+userDto.getUsername());
        User u = userDao.findByUsername(userDto.getUsername());
        return jwtService.generateToken(u);

	}
	
}
