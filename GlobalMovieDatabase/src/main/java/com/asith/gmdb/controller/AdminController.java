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
import com.asith.gmdb.entity.User;
import com.asith.gmdb.service.AdminService;
import com.asith.gmdb.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@GetMapping("/adminLogin")
	public ModelAndView viewLoginForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminLogin");
		mv.addObject("admin", new Admin());
		
		return mv;
	}

	@PostMapping("/adminLogin")
	public ModelAndView processLoginForm(@ModelAttribute("admin") Admin admin, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Admin verifiedAdmin = adminService.verifyAdmin(admin);
		
		if(verifiedAdmin != null) {
			mv.setViewName("redirect:adminPage");
			session.setAttribute("admin", verifiedAdmin);
		}
		else {
			mv.setViewName("adminLogin");
			mv.addObject("errorMessage", "Password incorrect!");
		}
		
		return mv;
	}

	@GetMapping("/adminPage")
	public ModelAndView adminPage(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("adminPage");
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/adminChangePassword")
	public ModelAndView viewPasswordForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("adminChangePassword");
			mv.addObject("admin", new Admin());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/adminChangePassword")
	public ModelAndView processPasswordForm(@Valid @ModelAttribute("admin") Admin admin, BindingResult result, HttpSession session, String cpassword) {
		Admin sessionAdmin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(sessionAdmin != null) {
			if(result.hasErrors()) {
				mv.setViewName("adminChangePassword");
			}
			else {
				if(adminService.verifyPasswords(admin.getAdminPassword(), cpassword)) {
					adminService.updateAdmin(admin);
					mv.setViewName("adminPage");
				}
				else {
					mv.setViewName("adminChangePassword");
					mv.addObject("errorMessage", "Passwords do not match!");
				}
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/viewUsers")
	public ModelAndView viewUsersPage(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("viewUsers");
			mv.addObject("users", userService.getAllUsers());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@GetMapping("/searchUser")
	public ModelAndView viewSearchUserForm(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			mv.setViewName("searchUser");
			mv.addObject("user", new User());
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}

	@PostMapping("/searchUser")
	public ModelAndView processSearchUserForm(@ModelAttribute("user") User user, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		ModelAndView mv = new ModelAndView();
		
		if(admin != null) {
			User getUser = userService.getUserAndMovies(user);
			
			if(getUser != null) {
				mv.setViewName("viewUser");
				mv.addObject("user", getUser);
			}
			else {
				mv.setViewName("searchUser");
				mv.addObject("errorMessage", "User does not exist!");
			}
		}
		else {
			mv.setViewName("redirect:login");
		}
		
		return mv;
	}
}
