package com.turing.javaee8.jpamvc.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.model.dto.TitleAndYear;
import com.turing.javaee8.jpamvc.model.dto.TitleWithYear;

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
	//@Query("select m from Movie m")
	@Query("select m from Movie m join details left join actors")
	List<Movie> getAllMovies();
	
	@Query("select m.title as title,m.year as year from Movie m")
	List<TitleAndYear> getAllMovieTitleAndYear();
	
	@Query("select m.title as title from Movie m")
	List<String> getAllMovieTitle();
	
	@Query("select m.title as title,m.year as year from Movie m")
	List<TitleWithYear> getAllMovieTitleAndYearWithRecord();
	
	@Query("select m.details.details from Movie m join details ")
	List<String> getAllMovieDetails();
	
	@Query("select m from Movie m where m.title like %:title%")
	List<Movie> getAllMoviesByTitle(String title);
	
	@Query("select m from Movie m left join actors act where act.firstName like %:firstName%")
	List<Movie> getAllMovieWithActor(String firstName);
	
	
	@Query("select m from Movie m left join actors act left join fetch directors where act.firstName like %:firstName%")
	List<Movie> getAllMovieWithActorLazy(String firstName);
	
	@Query("select count(m) from Movie m")
	Long getTotalMovie();
	
	@Query("select m.genre from Movie m group by m.genre having count(m.genre)>=:commentCount ")
	List<String> getAllMovieWithGenreGte(Integer commentCount);
	
	@Query("select distinct m.genre from Movie m")
	List<String> getAllMovieGenere();
	
	@Query("select m from Movie m order by m.year desc")
	List<Movie> getAllMovieOrderByYear();
	
	@Query("select m from Movie m limit :rowCount")
	List<Movie> getAllMovieLimit(int rowCount);
	
	@Query(value="SELECT * FROM Movie",nativeQuery=true)
	List<Movie> getAllMovieWithNative();
}
