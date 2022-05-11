package com.filmRecommender.repository;
import java.util.List;

import com.filmRecommender.model.Pais;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface PaisRepository extends Neo4jRepository<Pais, String>{

    @Query("MATCH (p:Pais) " +
        "RETURN DISTINCT p.nombre")
    List<String> getPaises();
    
}
