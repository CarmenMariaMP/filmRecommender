package com.filmRecommender.model.relationships_models;

import java.util.List;

import com.filmRecommender.model.Compositor;
import com.filmRecommender.model.Duracion;
import com.filmRecommender.model.Pelicula;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RelationshipEntity(type = "dura")
@Getter
@Setter
@NoArgsConstructor
public class DuracionRelationship {
    @Id @GeneratedValue   
    private Long paisRelationshipId;

    @Property  
    private Double peso;

    @StartNode 
    private Pelicula pelicula;

    @EndNode   
    private Duracion duracion;
}
