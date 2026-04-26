package com.turing.javaee8.jpamvc.repository.querymethod;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.turing.javaee8.jpamvc.repository.ActorDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Rollback(false)
@Slf4j
@SpringBootTest
public class ActorDaoJPQLTest {
	@Autowired
	ActorDao actorDao;
	
	@Test
	@Transactional
	void testGetAllGender()
	{
		List<String> genders = this.actorDao.getAllGender();
		genders.forEach(System.err::println);
	}
}
