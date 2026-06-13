package com.turing.javaee8.webfluxmongo.security;


import org.springframework.security.core.userdetails.UserDetails;

public interface TokenProvider {
    String generateToken(UserDetails userDetails);
}