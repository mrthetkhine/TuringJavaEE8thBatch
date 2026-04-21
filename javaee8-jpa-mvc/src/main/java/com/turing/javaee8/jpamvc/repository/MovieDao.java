package com.turing.javaee8.jpamvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.jpamvc.model.Movie;

@Repository
public interface MovieDao extends JpaRepository<Movie, Long>{
	List<Movie> findByTitle(String title);
	List<Movie> findByGenreAndYear(String genre,Integer year);
}
