package com.turing.javaee8.jpamvc.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.jpamvc.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book,Long>
{
	
}
