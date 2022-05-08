package com.filmRecommender.repository;
import java.util.List;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.filmRecommender.model.*;

public interface FilmRepository extends Neo4jRepository<Pelicula, String>{

    @Query("MATCH (pelicula:Pelicula)-[:dirigida]->(d:Director) " +
    "WHERE d.name = $0 " +
        "RETURN pelicula")
    List<Pelicula> findFilmsByDirector(String nombreDirector);

    @Query("MATCH (pelicula:Pelicula)-[:dirigida]->(g:Guionista) " +
    "WHERE d.name = $0 " +
        "RETURN pelicula")
    List<Pelicula> findFilmsByGuionista(String nombreGuionista);
}
