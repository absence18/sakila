package com.gd.sakila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gd.sakila.service.FilmService;

@Controller
public class FilmController {
	@Autowired FilmService filmService;
	
	@GetMapping("/getFilmOne")
	public String getFilmOne() {
		filmService.getFilmOne(1, 1);
		return "getFilmOne";
	}
}