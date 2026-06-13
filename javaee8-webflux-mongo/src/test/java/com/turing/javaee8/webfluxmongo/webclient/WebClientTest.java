package com.turing.javaee8.webfluxmongo.webclient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import com.turing.javaee8.webfluxmongo.operator.Delay;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

//https://fakeapi.platzi.com/en/rest/auth-jwt/
public class WebClientTest {
	HttpClient httpClient = HttpClient
			  .create()
			  .responseTimeout(Duration.ofMillis(1000))
			  /*
			  .doOnConnected(conn -> conn
					    .addHandler(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS))
					    .addHandler(new WriteTimeoutHandler(10)))
					    */
			  .wiretap(true);
	WebClient webClient = WebClient.builder()
			  .clientConnector(new ReactorClientHttpConnector(httpClient))
			  .build();
	
	Mono<AuthResponse> login(UserDto user)
	{
		return webClient
		   .post()
		   .uri("https://api.escuelajs.co/api/v1/auth/login")
		   //.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		   .bodyValue(user)
		   .retrieve()
		   .bodyToMono(AuthResponse.class)
		   .onErrorResume(err->Mono.error(new RuntimeException("Invalid login")));
	}
	Mono<ProfileResponse> getProfile(String accessToken)
	{
		return webClient
				   .get()
				   .uri("https://api.escuelajs.co/api/v1/auth/profile")
				   .headers(headers -> headers.setBearerAuth(accessToken))
				   .retrieve()
				   .bodyToMono(ProfileResponse.class)
				   .log();
	}
	@Test
	public void testGetProfile()
	{
		UserDto user = new UserDto();
		user.setEmail("john@mail.com");
		user.setPassword("changeme");
		
		
		this.login(user)
			.flatMap(loginResponse->this.getProfile(loginResponse.access_token))
			.subscribe(data->{
				System.out.println("Profile "+data);
			},err->{
				System.out.println("Error "+err.getMessage());
			});
			
	
		Delay.delay(4000);
	}
}
