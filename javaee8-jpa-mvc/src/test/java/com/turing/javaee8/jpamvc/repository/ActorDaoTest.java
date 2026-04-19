package com.turing.javaee8.jpamvc.repository;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.turing.javaee8.jpamvc.model.Actor;
import com.turing.javaee8.jpamvc.model.Gender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ActorDaoTest {

	@Autowired
	ActorDao actorDao;
	
	@Test
	public void testFindAll()
	{
		List<Actor> actors =this.actorDao.findAll();
		actors.forEach(System.out::println);
	}
	//@Test
	public void testSave()
	{
		Actor actor = new Actor();
		
		actor.setFirstName("Kate");
		actor.setLastName("WinSlet");
		actor.setGender(Gender.Female);
		
		Date birthDate = new GregorianCalendar(1975, 10, 5).getTime();
		actor.setBirthday(	birthDate);
		
		this.actorDao.save(actor);
		
	}
}
