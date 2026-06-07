package com.turing.javaee8.webfluxmongo.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.turing.javaee8.webfluxmongo.model.Actor;
import com.turing.javaee8.webfluxmongo.model.Movie;

import reactor.core.publisher.Flux;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie,String>{
	Flux<Movie> findByYear(int year);

	Flux<Movie> findByGenres(String genre);
	
	@Query("{ 'year' : ?0 }")
    Flux<Movie> getMovieWithYear(int year);
	
	@Query("{"
			+ "    genres:{"
			+ "        $elemMatch: { $eq: ?0 }"
			+ "    }  "
			+ "}")
	Flux<Movie> getMovieWithGenre(String genre);
	
	@Query("{"
			+ "    'genres.?0':{ $exists: true } "
			+ "}")
	Flux<Movie> getMovieWithGenreSize(int size);
	
	@Aggregation(pipeline={"{\n"
	 		+ "        $addFields: {\n"
	 		+ "            \"movie_actors\": {\n"
	 		+ "                $map:{\n"
	 		+ "                    input: \"$actors\",\n"
	 		+ "                    as: \"actor\",\n"
	 		+ "                    in: '$$actor.$id'\n"
	 		+ "                }\n"
	 		+ "                \n"
	 		+ "            }\n"
	 		+ "        }\n"
	 		+ "    },\n"
	 		,
	 		 "    {\n"
	 		+ "        $unwind : \"$movie_actors\" \n"
	 		+ "    },\n"
	 		,
	 		 "    { \n"
	 		+ "        \"$lookup\": {\n"
	 		+ "            \"from\": \"actors\",\n"
	 		+ "            \"localField\": \"movie_actors\",\n"
	 		+ "            \"foreignField\": \"_id\",\n"
	 		+ "            \"as\": \"joined_actors\"\n"
	 		+ "        } \n"
	 		+ "    }"
	 		,
	 		 "    { \n"
	 		+ "        \"$group\": {\n"
	 		+ "            \"_id\": \"$_id\",\n"
	 		+ "            \"joined_actors\":{ \n"
	 		+ "                                \"$push\":{ $arrayElemAt: [ \"$joined_actors\",0] }\n"
	 		+ "                            },\n"
	 		+ "            \"doc\":{\"$first\":\"$$ROOT\"}\n"
	 		+ "        }\n"
	 		+ "    }"
	 		,
	 		 "    {   \"$replaceRoot\":{\n"
	 		+ "            \"newRoot\":{ $mergeObjects: [ '$doc',{ actors: '$joined_actors' }] },\n"
	 		+ "        }\n"
	 		+ "    }"
	 		,
	 		" { $unset: \"joined_actors\" }"
	 		,
	 		 "     { \n"
	 		+ "        \"$match\": {\n"
	 		+ "            \"actors\": {\n"
	 		+ "                $elemMatch:{\n"
	 		+ "                    \"firstName\":?0"
	 		+ "                }\n"
	 		+ "            }\n"
	 		+ "        } \n"
	 		+ "    }"})
	 Flux<Movie> getAllMovieWithLookup(String actorName);
	
	@Query("{\n"
			+ "    'actors.$id': ObjectId(?0)"
			+ "}")
	Flux<Movie> getMovieWithActorId(String id);
}
