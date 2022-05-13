package com.filmRecommender.repository;

import java.util.List;

import com.filmRecommender.model.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface ActorRepository extends Neo4jRepository<Actor, String> {

	@Query("MATCH (a:Actor) " + "RETURN DISTINCT a.nombre")
	List<String> getActores();

}
