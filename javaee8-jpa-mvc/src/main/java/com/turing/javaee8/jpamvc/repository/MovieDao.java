package com.turing.javaee8.jpamvc.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.jpamvc.model.Movie;

@Repository
public interface MovieDao extends JpaRepository<Movie, Long>{
	List<Movie> findByTitle(String title);
	List<Movie> findByTitleLike(String title);
	List<Movie> findByTitleIgnoreCase(String title);
	List<Movie> findByGenreAndYear(String genre,Integer year);
	List<Movie> findByGenreOrderByYearDesc(String genre);
	List<Movie> findByYearLessThan(Integer year);
	List<Movie> findByYearAfter(Integer year);
	List<Movie> findByGenreNot(String genre);
	List<Movie> findByGenreIn(Collection<String> genres);
	
	@Query("select m from Movie m where m.id = ?1/*here is comment*/")
	Movie getMovieById(Long id);
	
	@Query("select m from Movie m where m.year=:year")
	List<Movie> getMovieByYear(Integer year);
	
	@Modifying
	@Query("update Movie m set m.genre = :genre where m.id = :id")
	int updateMovieGenreById(Long id, String genre);
	
	@Modifying
	@Query("delete Movie m where m.id = :id")
	int deleteMovieByid(Long id);
	
	@Modifying
	@Query("insert into Movie(title,year,genre) values(:title,:year,:genre)")
	int insertMovie(String title, int year,String genre);
	
	//@Query("from Movie m")
	@Query("select m from Movie m")
	List<Movie> getAllMovies();
}
