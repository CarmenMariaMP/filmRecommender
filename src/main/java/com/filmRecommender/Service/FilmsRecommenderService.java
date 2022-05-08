package com.filmRecommender.Service;


import java.util.Collection;

import com.filmRecommender.model.Pelicula;
import com.filmRecommender.repository.FilmRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmsRecommenderService {
    
    @Autowired
	FilmRepository filmRepository;

    public  void  runExample(){
        System.out.println("Peliculas con el director: ");
        Collection<Pelicula> films = filmRepository.findFilmsByDirector("Michael Curtiz");
        System.out.println(films);


    }
    
}
