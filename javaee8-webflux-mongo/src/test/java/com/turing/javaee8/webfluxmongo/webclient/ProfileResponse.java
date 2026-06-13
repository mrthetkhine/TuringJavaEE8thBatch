package com.turing.javaee8.webfluxmongo.webclient;

import lombok.Data;

@Data
public class ProfileResponse {
	Long id;
	String name;
	String role;
	String avatar;
}
