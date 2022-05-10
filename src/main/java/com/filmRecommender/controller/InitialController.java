package com.filmRecommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Controller
public class InitialController {

	
	@GetMapping("/inicio")
    public String index() {
		return "listado";
	}
}
