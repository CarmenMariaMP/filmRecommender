package com.filmRecommender.repository;

import java.util.List;

import com.filmRecommender.model.Guionista;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface GuionistaRepository extends Neo4jRepository<Guionista, String> {

	@Query("MATCH (g:Guionista) " + "RETURN DISTINCT g.nombre")
	List<String> getGuionistas();

}
