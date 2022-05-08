package com.filmRecommender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.filmRecommender.repository")
public class FilmRecommenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmRecommenderApplication.class, args);
	}

}
