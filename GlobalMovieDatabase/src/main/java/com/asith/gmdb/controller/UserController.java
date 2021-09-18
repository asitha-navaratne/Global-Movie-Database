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

import com.asith.gmdb.entity.User;
import com.asith.gmdb.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public ModelAndView viewLoginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("user", new User());
		
		return mv;
	}

	@PostMapping("/login")
	public ModelAndView processLoginForm(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User verifiedUser = userService.verifyUser(user);
		
		if(verifiedUser != null) {
			mv.setViewName("redirect:profile");
			session.setAttribute("user", verifiedUser);
		}
		else {
			mv.setViewName("login");
			mv.addObject("errorMessage", "Username or Password incorrect!");
		}
		
		return mv;
	}

	@GetMapping("/register")
	public ModelAndView viewRegisterForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		mv.addObject("user", new User());
		
		return mv;
	}

	@PostMapping("/register")
	public ModelAndView processRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult result, String cpassword) {
		ModelAndView mv = new ModelAndView();
		
		if(result.hasErrors()) {
			mv.setViewName("register");
		}
		else {
			if(userService.verifyPasswords(user.getUserPassword(), cpassword)) {
				if(userService.saveUser(user)) {
					mv.setViewName("redirect:login");
				}
				else {
					mv.setViewName("register");
					mv.addObject("errorMessage", "Username already exists!");
				}
			}
			else {
				mv.setViewName("register");
				mv.addObject("errorMessage", "Passwords do not match!");
			}
		}
		
		return mv;
	}

	@GetMapping("/profile")
	public ModelAndView profilePage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			mv.setViewName("profile");
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/editDetails")
	public ModelAndView viewEditDetailsForm(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			mv.setViewName("editDetails");
			mv.addObject("user", user);
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/editDetails")
	public ModelAndView processEditDetailsForm(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(sessionUser != null) {
			if(result.hasErrors()) {
				mv.setViewName("editDetails");
			}
			else {
				userService.updateUser(user);
				mv.setViewName("redirect:profile");
				session.setAttribute("user", user);
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/changePassword")
	public ModelAndView viewChangePasswordForm(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			mv.setViewName("changePassword");
			mv.addObject("user", user);
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/changePassword")
	public ModelAndView processChangePasswordForm(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, String cpassword) {
		User sessionUser = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(sessionUser != null) {
			if(result.hasErrors()) {
				mv.setViewName("changePassword");
			}
			else {
				if(userService.verifyPasswords(user.getUserPassword(), cpassword)) {
					userService.updateUser(user);
					mv.setViewName("redirect:login");
					session.setAttribute("user", user);
				}
				else {
					mv.setViewName("changePassword");
					mv.addObject("errorMessage", "Passwords do not match!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/userMovies")
	public ModelAndView userMoviesPage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		
		if(user != null) {
			mv.setViewName("userMovies");
			mv.addObject("user", userService.getUserAndMovies(user));
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}
}
