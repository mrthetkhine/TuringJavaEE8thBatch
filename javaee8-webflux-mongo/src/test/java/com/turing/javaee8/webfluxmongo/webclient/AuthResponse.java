package com.turing.javaee8.webfluxmongo.webclient;

import lombok.Data;

@Data
public class AuthResponse {
	String access_token;
	String refreshToken;
}
