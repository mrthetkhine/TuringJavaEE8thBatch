package com.turing.javaee8.jpamvc.service;

import com.turing.javaee8.jpamvc.model.dto.UserDto;

public interface AuthService {
	public void register(UserDto userDto)throws Exception ;
	public String login(UserDto userDto); 
}
