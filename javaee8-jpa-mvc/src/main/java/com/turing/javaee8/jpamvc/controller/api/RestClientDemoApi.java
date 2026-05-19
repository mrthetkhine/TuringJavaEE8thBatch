package com.turing.javaee8.jpamvc.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.turing.javaee8.jpamvc.controller.api.common.ApiSuccessResponse;
import com.turing.javaee8.jpamvc.controller.api.common.ApiUtil;
import com.turing.javaee8.jpamvc.controller.api.common.SuccessCode;
import com.turing.javaee8.jpamvc.model.dto.MovieDto;
import com.turing.javaee8.jpamvc.model.dto.TodoDto;
import com.turing.javaee8.jpamvc.service.UnstableService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/demo")
public class RestClientDemoApi {
	RestClient restClient = RestClient.create();
	
	@Autowired
	ApiUtil apiUtil;
	
	String uriBase = "https://jsonplaceholder.typicode.com/todos";
	
	@Autowired
	UnstableService unstableService;
	
	@GetMapping(value="/todos/{id}")
	ResponseEntity<ApiSuccessResponse<TodoDto>> getTodo(@PathVariable Long id)
	{
		/*for the sake of tutorial we don't create service
		 * */
		log.info("getTodo "+id);
		TodoDto response = restClient
				  .get()
				  .uri(uriBase + "/"+id)
				  .retrieve()
				  .body(TodoDto.class);
				  
				 
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
					SuccessCode.SUCESS.toString(),
					"Todo ok",response);
	}
	
	@PostMapping(value="/todos")
	ResponseEntity<ApiSuccessResponse<TodoDto>> savePost()
	{
		/*for the sake of tutorial we don't create service
		 * */
		TodoDto dto = new TodoDto();
		dto.setId(2L);
		dto.setTitle("Test");
		dto.setCompleted(true);
		
		log.info("save post ");
		TodoDto response = restClient
				  .post()
				  .uri(uriBase )
				  .body(dto)
				  .retrieve()
				  .body(TodoDto.class);
				  
				 
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
					SuccessCode.SUCESS.toString(),
					"Todo save ok",response);
	}
	@GetMapping(value="/data")
	ResponseEntity<ApiSuccessResponse<String>> retryDemo()
	{
		/*for the sake of tutorial we don't create service
		 * */
		String data = this.unstableService.getData();
		return this.apiUtil.buildSucessResponse(HttpStatus.OK,
					SuccessCode.SUCESS.toString(),
					"Todo ok",data);
	}
}
