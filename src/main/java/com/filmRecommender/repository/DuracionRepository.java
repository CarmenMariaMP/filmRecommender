package com.filmRecommender.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.filmRecommender.model.Duracion;

public interface DuracionRepository extends Neo4jRepository<Duracion, String> {

	@Query("MATCH (d:Duracion)<-[:dura]-(p:Pelicula) " + "Where p.nombre = $0 " + "RETURN DISTINCT d.duracion LIMIT 1")
	String getDuracion(String nombrePelicula);

}
