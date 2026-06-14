package com.turing.javaee8.webfluxmongo.service;



import com.turing.javaee8.webfluxmongo.dto.TokenResponse;
import com.turing.javaee8.webfluxmongo.model.User;

import reactor.core.publisher.Mono;

public interface AuthService {
	Mono<TokenResponse> register(User user);
	Mono<TokenResponse> login(User user);

}
