package com.turing.javaee8.jpamvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.jpamvc.model.Actor;
import com.turing.javaee8.jpamvc.model.Book;

@Repository
public interface ActorDao extends JpaRepository<Actor,Long>{
	@Query("select a.gender from Actor a ")
	List<String> getAllGender();
}
