package com.asith.gmdb.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asith.gmdb.entity.Admin;
import com.asith.gmdb.entity.Director;
import com.asith.gmdb.service.DirectorService;

@Controller
public class DirectorController {

	@Autowired
	private DirectorService directorService;

	@GetMapping("/addDirector")
	public ModelAndView viewAddForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("addDirector");
			mv.addObject("director", new Director());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/addDirector")
	public ModelAndView processAddForm(@Valid @ModelAttribute("director") Director director, BindingResult result, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			if(result.hasErrors()) {
				mv.setViewName("addDirector");
			}
			else {
				if(directorService.saveDirector(director)) {
					mv.setViewName("redirect:addMovieDetails");
				}
				else {
					mv.setViewName("addDirector");
					mv.addObject("errorMessage", "Director already exists!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/removeDirector")
	public ModelAndView viewRemoveForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("removeDirector");
			mv.addObject("director", new Director());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/removeDirector")
	public ModelAndView processRemoveForm(@Valid @ModelAttribute("director") Director director, BindingResult result, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			if(result.hasErrors()) {
				mv.setViewName("removeDirector");
			}
			else {
				if(directorService.deleteDirector(director)) {
					mv.setViewName("redirect:removeMovieDetails");
				}
				else {
					mv.setViewName("removeDirector");
					mv.addObject("errorMessage", "Director does not exist!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}
}
