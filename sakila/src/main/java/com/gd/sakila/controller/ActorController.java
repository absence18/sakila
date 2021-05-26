package com.gd.sakila.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.ActorService;
import com.gd.sakila.vo.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class ActorController {
	@Autowired ActorService actorService;

	@GetMapping("/addActor")
	public String addActor() {
		return "addActor";
	}
	@PostMapping("/addActor")
	public String addActor(Actor actor) {
		int row = actorService.addActor(actor);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorController.addActor 매개변수 row : " + row);
		return "redirect:/admin/getActorList";
	}

	@GetMapping("/getActorList")
	public String getActorList(Model model,
								@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
								@RequestParam(value="rowPerPagte", defaultValue = "10")int rowPerPage,
								@RequestParam(value = "searchWord", required = false) String searchWord) {

		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorController.getActorList 매개변수 currentPage : " + currentPage);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorController.getActorList 매개변수 rowPerPage : " + rowPerPage);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorController.getActorList 매개변수 searchWord : " + searchWord);

		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("currentPage", currentPage);
		parmMap.put("rowPerPage", rowPerPage);
		parmMap.put("searchWord", searchWord);

		Map<String, Object> actorMap = actorService.getActorList(parmMap);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶▶ActorController.getActorList actorMap : " + actorMap);

		model.addAttribute("currentPage",currentPage);
		model.addAttribute("searchWord",searchWord);
		model.addAttribute("actorList",actorMap.get("actorList"));
		model.addAttribute("lastPage", actorMap.get("lastPage"));
		return "getActorList";
	}
}