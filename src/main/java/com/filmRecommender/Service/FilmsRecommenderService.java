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
        System.out.println("Peliculas con el director: Clive_Donner");
        Collection<Pelicula> filmsDirector = filmRepository.findFilmsByDirector("Clive_Donner");
        filmsDirector.forEach(film -> System.out.println(film.getNombre()));
        System.out.println("Peliculas con el guionista: Rich Eustis");
        Collection<Pelicula> filmsGuionista = filmRepository.findFilmsByGuionista("Rich Eustis");
        filmsGuionista.forEach(film -> System.out.println(film.getNombre()));
        System.out.println("Peliculas con el compositor: Nagesh Kukunoor");
        Collection<Pelicula> filmsCompositor = filmRepository.findFilmsByCompositor("Nagesh Kukunoor");
        filmsCompositor.forEach(film -> System.out.println(film.getNombre()));
        System.out.println("Peliculas del pais: France");
        Collection<Pelicula> filmsPais = filmRepository.findFilmsByPais("France");
        filmsPais.forEach(film -> System.out.println(film.getNombre()));
        System.out.println("Peliculas con el actor: Jack_Klaff");
        Collection<Pelicula> filmsActor = filmRepository.findFilmsByActor("Jack_Klaff");
        filmsActor.forEach(film -> System.out.println(film.getNombre()));
    }
}
