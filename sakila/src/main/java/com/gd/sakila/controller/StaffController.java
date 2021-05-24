package com.gd.sakila.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.StaffList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class StaffController {
	@Autowired StaffService staffService;

	@GetMapping("/getStaffList")
	public String getStaffList(Model model,
							   @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
							   @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage,
							   @RequestParam(value = "searchWord", required = false) String searchWord) {
		log.debug("▶▶▶▶▶▶▶▶▶▶▶ getStaffList() currentPage : "+currentPage);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶ getStaffList() rowPerPage : "+rowPerPage);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶ getStaffList() searchWord : "+searchWord);

		List<StaffList> staffList = staffService.getStaffList(currentPage, rowPerPage, searchWord);
		log.debug("▶▶▶▶▶▶▶▶▶▶▶ getStaffList() staffList : "+staffList.toString());

		model.addAttribute("currentPage", currentPage);
		// model.addAttribute("lastPage", map.get("lastPage")); 아직 가공하지 않은상태
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("staffList", staffList);

		return "getStaffList";
	}
}