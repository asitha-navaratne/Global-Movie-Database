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

import com.asith.gmdb.entity.Actor;
import com.asith.gmdb.entity.Admin;
import com.asith.gmdb.service.ActorService;

@Controller
public class ActorController {

	@Autowired
	private ActorService actorService;

	@GetMapping("/addActor")
	public ModelAndView viewAddForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("addActor");
			mv.addObject("actor", new Actor());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/addActor")
	public ModelAndView processAddForm(@Valid @ModelAttribute("actor") Actor actor, BindingResult result, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			if(result.hasErrors()) {
				mv.setViewName("addActor");
			}
			else {
				if(actorService.saveActor(actor)) {
					mv.setViewName("redirect:addMovieDetails");
				}
				else {
					mv.setViewName("addActor");
					mv.addObject("errorMessage", "Actor already exists!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/removeActor")
	public ModelAndView viewRemoveForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("removeActor");
			mv.addObject("actor", new Actor());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/removeActor")
	public ModelAndView processRemoveForm(@Valid @ModelAttribute("actor") Actor actor, BindingResult result, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			if(result.hasErrors()) {
				mv.setViewName("removeActor");
			}
			else {
				if(actorService.deleteActor(actor)) {
					mv.setViewName("redirect:removeMovieDetails");
				}
				else {
					mv.setViewName("removeActor");
					mv.addObject("errorMessage", "Actor does not exist!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}
}
