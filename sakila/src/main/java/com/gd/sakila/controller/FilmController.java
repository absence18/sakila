package com.gd.sakila.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.FilmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class FilmController {
	@Autowired FilmService filmService;
	@GetMapping("/getFilmList")
	public String getFilmList(Model model,
							@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
							@RequestParam(value="rowPerPage", defaultValue = "10") int rowPerPage,
							@RequestParam(value="searchWord", required = false ) String searchWord,
							@RequestParam(value="category", required = false ) String category,
							@RequestParam(value="rating", required = false ) String rating) {
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmList 매개변수 currentPage : " + currentPage);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmList 매개변수 rowPerPage : " + rowPerPage);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmList 매개변수 searchWord : " + searchWord);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmList 매개변수 category : " + category);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmList 매개변수 rating : " + rating);
		
		Map<String, Object> filmMap = filmService.getFilmList(currentPage, rowPerPage, searchWord, category, rating);	
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmList filmMap.get(\"lastPage\"). : " + filmMap.get("lastPage"));
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmList filmMap.get(\"filmList\") : " + filmMap.get("filmList"));
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("category", category);
		model.addAttribute("rating", rating);
		model.addAttribute("categoryList", filmMap.get("categoryList"));
		model.addAttribute("ratingList", filmMap.get("ratingList"));
		model.addAttribute("lastPage", filmMap.get("lastPage"));
		model.addAttribute("filmList", filmMap.get("filmList"));
		
		return "getFilmList";
	}
	//
	@GetMapping("/getFilmOne")
	public String getFilmOne(Model model, 
							@RequestParam(value="filmId", required = true) int filmId) {
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmOne 매개변수 filmId : " + filmId);
		
		Map<String, Object> filmOneMap = filmService.getFilmOne(filmId);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶FilmController.getFilmOne filmOneMap : " + filmOneMap);
		model.addAttribute("store1Stock", filmOneMap.get("store1Stock"));
		model.addAttribute("store2Stock", filmOneMap.get("store2Stock"));
		model.addAttribute("filmOne", filmOneMap.get("filmOne"));
		
		return "getFilmOne";
	}
}