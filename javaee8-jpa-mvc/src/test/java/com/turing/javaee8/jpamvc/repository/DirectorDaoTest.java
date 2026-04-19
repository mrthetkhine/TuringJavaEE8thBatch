package com.turing.javaee8.jpamvc.repository;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.turing.javaee8.jpamvc.model.Actor;
import com.turing.javaee8.jpamvc.model.Address;
import com.turing.javaee8.jpamvc.model.Director;
import com.turing.javaee8.jpamvc.model.Gender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DirectorDaoTest {
	@Autowired
	DirectorDao directorDao;
	
	//@Test
	void testSave()
	{
		Director director = new Director();
		
		director.setFirstName("James");
		director.setLastName("Cameron");
		director.setGender(Gender.Male);
		
		Date birthDate = new GregorianCalendar(1954, 8, 16).getTime();
		director.setBirthday(	birthDate);
		
		this.directorDao.save(director);
	}
	@Test
	void testUpdate()
	{
		Optional<Director> result =this.directorDao.findById(1L);
		Director director = result.get();
		Address address = new Address();
		
		address.setCity("New York");
		address.setAddress("Somewhere in new york");
		
		director.setAddress(address);
		this.directorDao.save(director);
	}
	
}
