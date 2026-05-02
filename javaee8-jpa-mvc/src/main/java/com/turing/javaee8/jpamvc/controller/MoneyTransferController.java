package com.turing.javaee8.jpamvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turing.javaee8.jpamvc.service.MoneyTransferService;

@RestController
@RequestMapping(value="api")
public class MoneyTransferController {

	@Autowired
	MoneyTransferService transferService;
	
	@GetMapping(value="transfer/{from}/{to}/{amount}")
	String tranfer(@PathVariable Long from,@PathVariable Long to,@PathVariable Integer amount) throws Exception
	{
		this.transferService.transfer(from, to, amount);
		return "done";
	}
	
}
