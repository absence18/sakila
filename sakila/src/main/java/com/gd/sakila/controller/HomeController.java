package com.gd.sakila.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gd.sakila.service.StaffService;
import com.gd.sakila.vo.Staff;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@Autowired StaffService staffService;
	
	//Logger log  = LoggerFactory .getLogger(this.getClass()); 에노테이션으로 대체
	@GetMapping({"/", "/home", "/index"})
	public String home() {
		System.out.println("home controller");
		log.debug("test");
		return "home"; // 로그인
	}
	
	// logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Staff staff) {
		log.debug("★★★★ login() param staff :" + staff);
		
		Staff loginStaff = staffService.login(staff);
		log.debug("★★★★ login() param loginStaff :" + loginStaff);
		
		if(loginStaff != null) { // 로그인 실패
			session.setAttribute("loginStaff", loginStaff); // new Staff();
		}
		
		return "redirect:/";
		
	}
	
}