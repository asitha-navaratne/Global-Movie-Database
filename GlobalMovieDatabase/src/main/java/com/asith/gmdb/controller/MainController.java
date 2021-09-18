package com.asith.gmdb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asith.gmdb.entity.User;
import com.asith.gmdb.service.AdminService;

@Controller
public class MainController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/")
	public ModelAndView welcomePage() {
		adminService.createAdmin();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("user", new User());
		
		return mv;
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:login");
		
		return mv;
	}
}
