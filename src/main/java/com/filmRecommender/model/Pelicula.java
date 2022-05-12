package com.filmRecommender.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.Getter;
import lombok.Setter;

@Node
@Getter
@Setter
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @Property("nombre")
    private String nombre;

    @Relationship(type = "dirigida")
    private List<Director> directores;

    @Relationship(type = "guionizada")
    private List<Guionista> guionistas;

    @Relationship(type = "compuesta")
    private List<Compositor> compositor;

    @Relationship(type = "de")
    private List<Pais> paises;

    @Relationship(type = "dura")
    private Duracion duracion;

    @Relationship(type = "actua")
    private List<Actor> actores;
    
}
