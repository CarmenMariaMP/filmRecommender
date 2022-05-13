package com.filmRecommender.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.filmRecommender.model.*;
import com.filmRecommender.service.FilmsRecommenderService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InitialController {

	@Autowired
	FilmsRecommenderService filmRecommenderService;
	
	@GetMapping("/")
	public String inicion(ModelMap model) {
		return "index";
	}
	
	@GetMapping("/selector")
	public String selector(ModelMap model) {
		List<String> directores = filmRecommenderService.getDirectores();
		List<String> sortedDirectores = directores.stream().sorted().collect(Collectors.toList());
		model.addAttribute("directores", sortedDirectores);
		List<String> actores = filmRecommenderService.getActores();
		List<String> sortedActores = actores.stream().sorted().collect(Collectors.toList());
		model.addAttribute("actores", sortedActores);
		List<String> guionistas = filmRecommenderService.getGuionistas();
		List<String> sortedGuionistas = guionistas.stream().sorted().collect(Collectors.toList());
		model.addAttribute("guionistas", sortedGuionistas);
		List<String> compositores = filmRecommenderService.getCompositores();
		List<String> sortedCompositores = compositores.stream().sorted().collect(Collectors.toList());
		model.addAttribute("compositores", sortedCompositores);
		List<String> paises = filmRecommenderService.getPaises();
		List<String> sortedPaises = paises.stream().sorted().collect(Collectors.toList());
		model.addAttribute("paises", sortedPaises);
		return "selector";
	}

	@PostMapping("/recomendar")
	public String recomendar(@RequestParam HashMap<String, String> allParams,
			@RequestParam(value = "director", required = false) String director,
			@RequestParam(value = "guionista", required = false) String guionista,
			@RequestParam(value = "compositor", required = false) String compositor,
			@RequestParam(value = "actor", required = false) List<String> actor,
			@RequestParam(value = "pais", required = false) List<String> pais,
			@RequestParam(value = "duracion", required = false) String duracion, ModelMap model) {

		HashMap<String, Integer> recomendaciones = new HashMap<>();
		// Peliculas con dicho director
		if (director != null && director != "") {
			List<Pelicula> peliculas = filmRecommenderService.getPeliculasbyDirector(director);
			recomendaciones = actualizarRecomendaciones(recomendaciones, peliculas);
		}
		// Peliculas con dicho guionista
		if (guionista != null && guionista != "") {
			List<Pelicula> peliculas = filmRecommenderService.getPeliculasbyGuionista(guionista);
			recomendaciones = actualizarRecomendaciones(recomendaciones, peliculas);
		}
		// Peliculas con dicho compositor
		if (compositor != null && compositor != "") {
			List<Pelicula> peliculas = filmRecommenderService.getPeliculasbyCompositor(compositor);
			recomendaciones = actualizarRecomendaciones(recomendaciones, peliculas);
		}
		// Peliculas con dichos actores
		if (actor != null && actor.size() > 0) {
			for (int i = 0; i < actor.size(); i++) {
				List<Pelicula> peliculas = filmRecommenderService.getPeliculasbyActor(actor.get(i));
				recomendaciones = actualizarRecomendaciones(recomendaciones, peliculas);
			}
		}
		// Peliculas con dichos paises
		if (pais != null && pais.size() > 0) {
			for (int i = 0; i < pais.size(); i++) {
				List<Pelicula> peliculas = filmRecommenderService.getPeliculasbyPais(pais.get(i));
				recomendaciones = actualizarRecomendaciones(recomendaciones, peliculas);
			}
		}
		if (duracion != null) {
			if (StringUtils.isNumeric(duracion)) {

				Long duracionNumerica = Long.parseLong(duracion);
				for (String i : recomendaciones.keySet()) {
					Long duracion_pelicula = filmRecommenderService.getDuracionByFilm(i);

					duracion_pelicula = duracion_pelicula / (long) 60;
					if (duracion_pelicula > duracionNumerica || duracion_pelicula > 0L) {
						recomendaciones.put(i,
								recomendaciones.get(i) + 1);
					} else {
						recomendaciones.put(i,
								recomendaciones.get(i) + 2);
					}
				}
			}
		}
		// En el caso de que no haya ninguna pelicula que coincida con los criterios de
		// busqueda al listado se le pasara una lista vacia
		if (recomendaciones.keySet().size() > 0) {
			HashMap<String, Integer> sortedRecomendaciones = sortByValue(recomendaciones);
			List<Pelicula> topRecomendadas = new ArrayList<>();
			if (sortedRecomendaciones.size() > 3) {

				for (int i = 0; i < 3; i++) {
					Pelicula peliTop = filmRecommenderService.getPeliculabyName(sortedRecomendaciones.keySet().stream()
							.collect(Collectors.toList()).get(sortedRecomendaciones.size() - i - 1));
					Duracion d = peliTop.getDuracion();
					Duracion minutes = duracionToMinutes(d);
					peliTop.setDuracion(minutes);
					topRecomendadas.add(peliTop);
				}
			} else {

				for (int i = 0; i < sortedRecomendaciones.size(); i++) {
					Pelicula peliTop = filmRecommenderService.getPeliculabyName(sortedRecomendaciones.keySet().stream()
							.collect(Collectors.toList()).get(sortedRecomendaciones.size() - i - 1));
					Duracion d = peliTop.getDuracion();
					Duracion minutes = duracionToMinutes(d);
					peliTop.setDuracion(minutes);
					topRecomendadas.add(peliTop);
				}
			}
			System.out.println(topRecomendadas.get(0).getDirectores());
			model.addAttribute("topRecomendadas", topRecomendadas);
		} else {
			model.addAttribute("topRecomendadas", new ArrayList<Pelicula>());
		}
		return "listado";
	}

	private HashMap<String, Integer> actualizarRecomendaciones(HashMap<String, Integer> recomendaciones,
			List<Pelicula> peliculas) {
		for (int i = 0; i < peliculas.size(); i++) {
			if (!recomendaciones.containsKey(peliculas.get(i).getNombre())) {
				recomendaciones.put(peliculas.get(i).getNombre(), 1);
			} else {
				recomendaciones.put(peliculas.get(i).getNombre(),
						recomendaciones.get(peliculas.get(i).getNombre()) + 1);
			}
		}
		return recomendaciones;
	}

	// Codigo procedente de https://www.geeksforgeeks.org
	private HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
		HashMap<String, Integer> temp = hm.entrySet().stream()
				.sorted((i1, i2) -> i1.getValue().compareTo(i2.getValue())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return temp;
	}

	private Duracion duracionToMinutes(Duracion d) {
		Duracion duracion = new Duracion();
		String segundos = d.getDuracion();
		try {
			String arraySegundos [] = segundos.split("\\.");
			Long segundosNumber = Long.parseLong(arraySegundos[0]);
			Long minutosNumber = segundosNumber / (long) 60;
			duracion.setDuracion(minutosNumber.toString());
		} catch (NumberFormatException e) {
			duracion.setDuracion("No disponible");
		}
		return duracion;
	}
}
