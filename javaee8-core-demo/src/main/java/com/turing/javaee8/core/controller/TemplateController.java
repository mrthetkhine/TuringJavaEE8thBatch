package com.turing.javaee8.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turing.javaee8.core.model.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/demo")
public class TemplateController {
	
	@GetMapping
	String index(Model model)
	{
		log.info("Demo index");
		model.addAttribute("message", "Hi from controller");
		
		Book book = new Book(1L,"Fast and slow thinking","Dianel ",2010);
		
		model.addAttribute("book", book);
		model.addAttribute("admin", false);
		model.addAttribute("gender", "f");
		
		return "demo/index";
	}
}
