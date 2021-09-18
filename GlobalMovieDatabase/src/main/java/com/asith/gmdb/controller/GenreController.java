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
import com.asith.gmdb.entity.Genre;
import com.asith.gmdb.service.GenreService;

@Controller
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GetMapping("/addGenre")
	public ModelAndView viewAddForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("addGenre");
			mv.addObject("genre", new Genre());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/addGenre")
	public ModelAndView processAddForm(@Valid @ModelAttribute("genre") Genre genre, BindingResult result, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			if(result.hasErrors()) {
				mv.setViewName("addGenre");
			}
			else {
				if(genreService.saveGenre(genre)) {
					mv.setViewName("redirect:addMovieDetails");
				}
				else {
					mv.setViewName("addGenre");
					mv.addObject("errorMessage", "Genre already exists!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/removeGenre")
	public ModelAndView viewRemoveForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("removeGenre");
			mv.addObject("genre", new Genre());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/removeGenre")
	public ModelAndView processRemoveForm(@Valid @ModelAttribute("genre") Genre genre, BindingResult result, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			if(result.hasErrors()) {
				mv.setViewName("removeGenre");
			}
			else {
				if(genreService.deleteGenre(genre)) {
					mv.setViewName("redirect:removeMovieDetails");
				}
				else {
					mv.setViewName("removeGenre");
					mv.addObject("errorMessage", "Genre does not exist!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}
}
