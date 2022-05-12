package com.filmRecommender.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.filmRecommender.model.*;
import com.filmRecommender.service.FilmsRecommenderService;

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
	public String recomendar(@RequestParam(value = "director", required = false) String director,
			@RequestParam(value = "guionista", required = false) String guionista,
			@RequestParam(value = "compositor", required = false) String compositor,
			@RequestParam(value = "actor", required = false) List<String> actor,
			@RequestParam(value = "pais", required = false) List<String> pais, ModelMap model) {

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
		HashMap<String, Integer> sortedRecomendaciones = sortByValue(recomendaciones);
		List<Pelicula> topRecomendadas = new ArrayList<>();
		if (sortedRecomendaciones.size() > 3) {

			for (int i = 0; i < 3; i++) {
				topRecomendadas.add(filmRecommenderService.getPeliculabyName(sortedRecomendaciones.keySet().stream()
						.collect(Collectors.toList()).get(sortedRecomendaciones.size() - i -1)));
			}
		} else {

			for (int i = 0; i < sortedRecomendaciones.size(); i++) {
				topRecomendadas.add(filmRecommenderService.getPeliculabyName(sortedRecomendaciones.keySet().stream()
						.collect(Collectors.toList()).get(sortedRecomendaciones.size() - i -1)));
			}
		}
		System.out.println(topRecomendadas.get(0).getDirectores());
		model.addAttribute("topRecomendadas", topRecomendadas);
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
}
