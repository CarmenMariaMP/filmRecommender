package com.filmRecommender.model.relationships_models;

import java.util.List;

import com.filmRecommender.model.Pais;
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

@RelationshipEntity(type = "de")
@Getter
@Setter
@NoArgsConstructor
public class PaisRelationship {
    @Id @GeneratedValue   
    private Long paisRelationshipId;

    @Property  
    private Double peso;

    @StartNode 
    private Pelicula pelicula;

    @EndNode   
    private List<Pais> paises;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "-[:dirigida {peso:"+peso+"}]-> "+paises.get(0)+")";
    }
}
