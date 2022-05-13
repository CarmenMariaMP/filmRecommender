package com.filmRecommender.repository;

import java.util.List;

import com.filmRecommender.model.Compositor;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface CompositorRepository extends Neo4jRepository<Compositor, String> {

	@Query("MATCH (c:Compositor) " + "RETURN DISTINCT c.nombre")
	List<String> getCompositores();

}