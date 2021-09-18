package com.asith.gmdb.controller;

import java.time.Year;
import java.util.List;

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
import com.asith.gmdb.entity.Movie;
import com.asith.gmdb.entity.User;
import com.asith.gmdb.service.ActorService;
import com.asith.gmdb.service.DirectorService;
import com.asith.gmdb.service.GenreService;
import com.asith.gmdb.service.MovieService;
import com.asith.gmdb.service.UserService;

@Controller
public class MovieController {

	@Autowired
	private UserService userService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private DirectorService directorService;

	@Autowired
	private ActorService actorService;

	@GetMapping("/addMovie")
	public ModelAndView viewAddForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("addMovie");
			mv.addObject("movie", new Movie());
			mv.addObject("genreList", genreService.getAllGenres());
			mv.addObject("directorList", directorService.getAllDirectors());
			mv.addObject("actorList", actorService.getAllActors());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/addMovie")
	public ModelAndView processAddForm(@Valid @ModelAttribute("movie") Movie movie, BindingResult result, HttpSession session, String selectedGenres, String selectedDirectors, String selectedActors) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			if(result.hasErrors()) {
				mv.setViewName("addMovie");
				mv.addObject("genreList", genreService.getAllGenres());
				mv.addObject("directorList", directorService.getAllDirectors());
				mv.addObject("actorList", actorService.getAllActors());
			}
			else {
				movie.setGenres(genreService.getGenresFromList(selectedGenres));
				movie.setDirectors(directorService.getDirectorsFromList(selectedDirectors));
				movie.setActors(actorService.getActorsFromList(selectedActors));
				
				if(movieService.saveMovie(movie)) {
					mv.setViewName("redirect:adminPage");
				}
				else {
					mv.setViewName("addMovie");
					mv.addObject("errorMessage", "Movie already exists!");
					mv.addObject("genreList", genreService.getAllGenres());
					mv.addObject("directorList", directorService.getAllDirectors());
					mv.addObject("actorList", actorService.getAllActors());
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/removeMovie")
	public ModelAndView viewRemoveForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("removeMovie");
			mv.addObject("movie", new Movie());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/removeMovie")
	public ModelAndView processRemoveForm(@Valid @ModelAttribute("movie") Movie movie, BindingResult result, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			if(result.hasErrors()) {
				mv.setViewName("removeMovie");
			}
			else {
				if(movieService.deleteMovie(movie)) {
					mv.setViewName("redirect:adminPage");
				}
				else {
					mv.setViewName("removeMovie");
					mv.addObject("errorMessage", "Movie does not exist!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/viewMovies")
	public ModelAndView viewMovies(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			mv.setViewName("viewMovies");
			mv.addObject("movies", movieService.getAllMovies());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/purchaseMovie")
	public ModelAndView viewPurchasePage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			mv.setViewName("purchaseMovie");
			mv.addObject("movie", new Movie());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/purchaseMovie")
	public ModelAndView processPurchaseForm(@ModelAttribute("movie") Movie movie, HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			mv.setViewName("purchaseMovie");
			
			List<Movie> movieList = movieService.getMovies(movie);
			
			if(movie.getMovieName().isBlank()) {
				mv.addObject("errorMessage", "Enter valid movie name!");
			}
			else if(movieList.isEmpty()) {
				mv.addObject("errorMessage", "Movie does not exist!");
			}
			else {
				mv.addObject("movie", new Movie());
				mv.addObject("movieList", movieService.getMovies(movie));
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/addToUser")
	public ModelAndView addMovieToUser(HttpSession session, String movieName, Year movieYear) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			Movie movie = movieService.getMovie(movieName, movieYear);
			user = userService.getUserAndMovies(user);
			
			if(userService.addMovieToUser(user, movie)) {
				mv.setViewName("purchaseMovie");
				mv.addObject("movie", new Movie());
				mv.addObject("errorMessage", "Movie already purchased!");
			}
			else {
				mv.setViewName("redirect:profile");
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/viewMovie")
	public ModelAndView viewMoviePage(HttpSession session, String movieName, Year movieYear) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			Movie movie = movieService.getMovie(movieName, movieYear);
			mv.setViewName("viewMovie");
			mv.addObject("movie", movie);
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/addMovieDetails")
	public ModelAndView viewAddDetailsPage(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("addMovieDetails");
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/removeMovieDetails")
	public ModelAndView viewRemoveDetailsPage(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("removeMovieDetails");
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}
}
