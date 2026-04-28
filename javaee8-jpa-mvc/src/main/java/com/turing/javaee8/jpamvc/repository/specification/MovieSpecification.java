package com.turing.javaee8.jpamvc.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.turing.javaee8.jpamvc.model.Movie;
import com.turing.javaee8.jpamvc.model.Actor;
import com.turing.javaee8.jpamvc.model.Director;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class MovieSpecification {
	//Root<T> root, CriteriaQuery<?> query,CriteriaBuilder builder
	 public static Specification<Movie> getAllMovieInYear(long year) {
		    return (root, query, builder) -> {
		      return builder.equal(root.get("year"), year);
		    };
	}
	 public static Specification<Movie> getMovieByYearGt(Long i) {
			return new Specification<Movie>() {
				private static final long serialVersionUID = 1L;
	
				@Override
				public Predicate toPredicate(Root<Movie> root,
						CriteriaQuery<?> query, 
						CriteriaBuilder cb) {
					return cb.gt(root.<Long>get("year"), i);
					
				}
			
		};  
	 }
	 public static Specification<Movie> getMovieByTitleYear(String title,Long year) {
		    return (root, query, builder) -> {
		      List<Predicate> predicates = new ArrayList<>();
		      
		      if(title !=null)
		      {
		    	  predicates.add(builder.equal(root.get("title"),title));
		      }
		      if(year !=null)
		      {
		    	  predicates.add(builder.equal(root.get("year"), year));
		    	  
		      }
		      return builder.or(predicates);
		 };
	}
	 public static Specification<Movie> getAllMovieWithActorIn(String actor) {
		    return (root, query, cb) -> {
		    	Join<Movie, Actor> actors = root.join("actors");
		    	return cb.like(actors.get("fullName"), "%"+actor+"%");
		 };
	}
	 public static Specification<Movie> getAllMovieWithActorOrDirector(String actor,String directorName) {
		    return (root, query, cb) -> {
		    	Join<Movie, Actor> actors = root.join("actors",JoinType.LEFT);
		    	Join<Movie, Director> directors = root.join("directors",JoinType.LEFT);
		    	
		    	List<Predicate> predicates = new ArrayList<>();
		    	predicates.add(cb.like(actors.get("fullName"), "%"+actor+"%"));
		    	predicates.add(cb.like(directors.get("fullName"), "%"+directorName+"%"));
		    	
		    	return cb.or(predicates);
		 };
	}
	 public static Specification<Movie> getAllMovieWithDirector(String directorName) {
		    return (root, query, cb) -> {
		    	
		    	Join<Movie, Director> directors = root.join("directors");
		    
		    	return cb.like(directors.get("fullName"), "%"+directorName+"%");
		 };
	}
	 public static Specification<Movie> getAllMovieOrderByYear() {
		    return (root, query, cb) -> {
		    	
		    	query.orderBy(cb.desc(root.get("year")));
		    
		    	return query.getRestriction();
		 };
	}
	 /*
	 public static Specification<String> getAllGenereWithMovieGt(long genreCount) {
		    return (root, query, cb) -> {
		    	
		    	query.groupBy(root.get("genre"));
		    	query.having(cb.greaterThanOrEqualTo(
		    			cb.count(root.get("genre")),cb.literal(genreCount) ));
		    
		    	return query.getGroupRestriction();
		 };
	}
	*/
}
