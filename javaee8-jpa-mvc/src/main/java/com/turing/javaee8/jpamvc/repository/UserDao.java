package com.turing.javaee8.jpamvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.jpamvc.model.Book;
import com.turing.javaee8.jpamvc.model.User;

@Repository
public interface UserDao extends JpaRepository<User,Long>{
	User findByUsername(String username);
}
