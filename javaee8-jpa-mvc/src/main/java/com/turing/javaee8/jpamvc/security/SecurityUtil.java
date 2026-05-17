package com.turing.javaee8.jpamvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
	@Autowired
	PasswordEncoder encoder;
	
	public String getHash(String plainPassword)
	{
		return this.encoder.encode(plainPassword);
	}
}
