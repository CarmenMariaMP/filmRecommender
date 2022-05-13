package com.filmRecommender.service;

import java.util.List;

import com.filmRecommender.model.*;
import com.filmRecommender.repository.ActorRepository;
import com.filmRecommender.repository.CompositorRepository;
import com.filmRecommender.repository.DirectorRepository;
import com.filmRecommender.repository.DuracionRepository;
import com.filmRecommender.repository.FilmRepository;
import com.filmRecommender.repository.GuionistaRepository;
import com.filmRecommender.repository.PaisRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmsRecommenderService {

	@Autowired
	FilmRepository filmRepository;
	@Autowired
	DirectorRepository directorRepository;
	@Autowired
	CompositorRepository compositorRepository;
	@Autowired
	ActorRepository actorRepository;
	@Autowired
	GuionistaRepository guionistaRepository;
	@Autowired
	PaisRepository paisRepository;
	@Autowired
	DuracionRepository duracionRepository;

	public List<String> getDirectores() {
		return directorRepository.getDirectores();
	}

	public List<String> getCompositores() {
		return compositorRepository.getCompositores();
	}

	public List<String> getActores() {
		return actorRepository.getActores();
	}

	public List<String> getGuionistas() {
		return guionistaRepository.getGuionistas();
	}

	public List<String> getPaises() {
		return paisRepository.getPaises();
	}

	public List<Pelicula> getPeliculasbyDirector(String director) {
		return filmRepository.findFilmsByDirector(director);
	}

	public List<Pelicula> getPeliculasbyCompositor(String compositor) {
		return filmRepository.findFilmsByCompositor(compositor);
	}

	public List<Pelicula> getPeliculasbyActor(String actor) {
		return filmRepository.findFilmsByActor(actor);
	}

	public List<Pelicula> getPeliculasbyGuionista(String guionista) {
		return filmRepository.findFilmsByGuionista(guionista);
	}

	public List<Pelicula> getPeliculasbyPais(String pais) {
		return filmRepository.findFilmsByPais(pais);
	}

	public Pelicula getPeliculabyName(String nombrePelicula) {
		return filmRepository.findFilmByName(nombrePelicula);
	}

	public Long getDuracionByFilm(String nombrePelicula) {
		System.out.println(StringUtils.isNumeric(duracionRepository.getDuracion(nombrePelicula).trim()));
		Long result = 0L;
		try {

			result = Long.parseLong(duracionRepository.getDuracion(nombrePelicula).trim().split("\\.")[0]);
		} catch (NumberFormatException e) {
			return 0L;
		}
		return result;
	}
}
