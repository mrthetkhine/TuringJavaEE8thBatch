package com.turing.javaee8.jpamvc.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.turing.javaee8.jpamvc.model.dto.UserDto;
import com.turing.javaee8.jpamvc.service.AuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/auth")
public class AuthApiController {
	@Autowired
	AuthService authService;
	
	@PostMapping(path = "/register")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestBody UserDto registerRequest) throws Exception {
        authService.register(registerRequest);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody UserDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
