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
        Collection<Pelicula> filmsDirector = filmRepository.findFilmsByDirector("Clive_Donner");
        System.out.println(filmsDirector);
        Collection<Pelicula> filmsGuionista = filmRepository.findFilmsByGuionista("Rich Eustis");
        System.out.println(filmsGuionista);
        Collection<Pelicula> filmsCompositor = filmRepository.findFilmsByCompositor("Nagesh Kukunoor");
        System.out.println(filmsCompositor);
        Collection<Pelicula> filmsPais = filmRepository.findFilmsByPais("France");
        System.out.println(filmsPais);
        Collection<Pelicula> filmsActor = filmRepository.findFilmsByActor("Jack_Klaff");
        System.out.println(filmsActor);
        


    }
    
}
