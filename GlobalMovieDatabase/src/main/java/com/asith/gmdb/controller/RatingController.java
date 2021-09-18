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

import com.asith.gmdb.entity.Rating;
import com.asith.gmdb.entity.User;
import com.asith.gmdb.service.MovieService;
import com.asith.gmdb.service.RatingService;

@Controller
public class RatingController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private RatingService ratingService;

	@GetMapping("/rateMovie")
	public ModelAndView viewRatingForm(HttpSession session, String movieId) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			mv.setViewName("rateMovie");
			mv.addObject("movieId", movieId);
			
			Rating rating = ratingService.getRating(user.getUserId(), Long.parseLong(movieId));
			
			if(rating != null) {
				mv.addObject("rating", rating);
				mv.addObject("errorMessage", "Warning! You are editing an existing review");
			}
			else {
				mv.addObject("rating", new Rating());
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/rateMovie")
	public ModelAndView processRatingForm(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, HttpSession session, String movieId) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			if(result.hasErrors()) {
				mv.setViewName("rateMovie");
				mv.addObject("movieId", movieId);
			}
			else {
				rating.setUser(user);
				rating.setMovie(movieService.getMovie(Long.parseLong(movieId)));
				
				ratingService.saveRating(rating);
				mv.setViewName("redirect:profile");
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/deleteRating")
	public ModelAndView deleteRating(HttpSession session, String movieId) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			Rating rating = ratingService.getRating(user.getUserId(), Long.parseLong(movieId));
			if(rating != null) {
				ratingService.deleteRating(rating);
			}
			mv.setViewName("redirect:profile");
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}
}
