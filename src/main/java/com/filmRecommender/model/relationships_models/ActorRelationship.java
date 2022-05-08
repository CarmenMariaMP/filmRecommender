package com.filmRecommender.model.relationships_models;

import java.util.List;

import com.filmRecommender.model.Actor;
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

@RelationshipEntity(type = "actua")
@Getter
@Setter
@NoArgsConstructor
public class ActorRelationship {
    @Id @GeneratedValue   
    private Long actorRelationshipId;

    @Property  
    private Double peso;

    @StartNode 
    private Pelicula pelicula;

    @EndNode   
    private List<Actor> actores;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "-[:actua {peso:"+peso+"}]-> "+actores.get(0)+")";
    }
}
