package com.filmRecommender.controller;

import java.util.List;

import com.filmRecommender.model.*;
import com.filmRecommender.service.FilmsRecommenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class InitialController {

	@Autowired
	FilmsRecommenderService filmRecommenderService;
	
	@GetMapping("/selector")
    public String selector(ModelMap model) {
		List<Director> directores = filmRecommenderService.getDirectores();
		model.addAttribute("directores", directores);
		List<Actor> actores = filmRecommenderService.getActores();
		model.addAttribute("actores", actores);
		List<Guionista> guionistas = filmRecommenderService.getGuionistas();
		model.addAttribute("guionistas", guionistas);
		List<Compositor> compositores = filmRecommenderService.getCompositores();
		model.addAttribute("compositores", compositores);
		List<Pais> paises = filmRecommenderService.getPaises();
		model.addAttribute("paises", paises);
		return "selector";
	}

}
