package com.filmRecommender.controller;

import java.util.List;
import java.util.Map;

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
		model.addAttribute("directores", directores);
		List<String> actores = filmRecommenderService.getActores();
		model.addAttribute("actores", actores);
		List<String> guionistas = filmRecommenderService.getGuionistas();
		model.addAttribute("guionistas", guionistas);
		List<String> compositores = filmRecommenderService.getCompositores();
		model.addAttribute("compositores", compositores);
		List<String> paises = filmRecommenderService.getPaises();
		model.addAttribute("paises", paises);
		return "selector";
	}

	@PostMapping("/recomendar")
	public String recomendar(@RequestParam Map<List<String>,String> allParams,ModelMap model) {
		System.out.println("Parameters are " + allParams.entrySet());
		
		return "redirect:/selector";
	}
}
