package com.filmRecommender;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import com.filmRecommender.service.FilmsRecommenderService;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableNeo4jRepositories("com.filmRecommender.repository")
public class FilmRecommenderApplication implements  CommandLineRunner{

	@Autowired
	FilmsRecommenderService filmsRecommenderService;
	public static void main(String[] args) {
		SpringApplication.run(FilmRecommenderApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        filmsRecommenderService.runExample();
    }

}
