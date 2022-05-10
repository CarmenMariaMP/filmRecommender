package com.filmRecommender.repository;

import java.util.List;

import com.filmRecommender.model.Director;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface DirectorRepository extends Neo4jRepository<Director, String>{

    @Query("MATCH (d:Director) " +
        "RETURN DISTINCT d")
    List<Director> getDirectores();
    
}
