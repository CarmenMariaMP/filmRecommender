package com.filmRecommender.repository;
import java.util.List;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.filmRecommender.model.*;

public interface FilmRepository extends Neo4jRepository<Pelicula, String>{

    @Query("MATCH (pelicula:Pelicula)-[:dirigida]->(d:Director) " +
    "WHERE d.nombre = $0 " +
        "RETURN pelicula")
    List<Pelicula> findFilmsByDirector(String nombreDirector);

    @Query("MATCH (pelicula:Pelicula)-[:guionizada]->(g:Guionista) " +
    "WHERE g.nombre = $0 " +
        "RETURN pelicula")
    List<Pelicula> findFilmsByGuionista(String nombreGuionista);

    @Query("MATCH (pelicula:Pelicula)-[:compuesta]->(c:Compositor) " +
    "WHERE c.nombre = $0 " +
        "RETURN pelicula")
    List<Pelicula> findFilmsByCompositor(String nombreCompositor);

    @Query("MATCH (pelicula:Pelicula)-[:de]->(p:Pais) " +
    "WHERE p.nombre = $0 " +
        "RETURN pelicula")
    List<Pelicula> findFilmsByPais(String nombrePais);

    @Query("MATCH (pelicula:Pelicula)-[:actua]->(a:Actor) " +
    "WHERE a.nombre = $0 " +
        "RETURN pelicula")
    List<Pelicula> findFilmsByActor(String nombreActor);

    @Query("Match (pelicula:Pelicula)-[ac:actua]->(a:Actor), (pelicula:Pelicula)-[dir:dirigida]->(d:Director), (pelicula:Pelicula)-[gui:guionizada]->(g:Guionista), (pelicula:Pelicula)-[com:compuesta]->(c:Compositor), (pelicula:Pelicula)-[de:de]->(p:Pais)" + 
    " Where pelicula.nombre = $0 " + 
    "RETURN pelicula, collect(ac), collect(a), collect(dir), collect(d), collect(gui), collect(g), collect(com), collect(c), collect(de), collect(p)")
    Pelicula findFilmByName(String nombrePelicula);
}

