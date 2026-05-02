package com.turing.javaee8.jpamvc.repository.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.jpamvc.repository.BookDaoTest;
import com.turing.javaee8.jpamvc.service.MoneyTransferService;

import lombok.extern.slf4j.Slf4j;

@Rollback(false)
@Slf4j
@SpringBootTest
public class TestTransaction {
	@Autowired
	MoneyTransferService transferService;
	
	@Test
	void transferTest()
	{
		try
		{
			this.transferService.transfer(1L, 2L, -3000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
