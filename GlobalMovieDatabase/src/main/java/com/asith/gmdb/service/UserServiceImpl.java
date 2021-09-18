package com.asith.gmdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asith.gmdb.eao.UserEao;
import com.asith.gmdb.entity.Movie;
import com.asith.gmdb.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserEao userEao;

	@Override
	@Transactional
	public boolean checkUsernameExists(User user) {
		List<User> userList = userEao.getUser(user.getUserName());
		
		return userList.isEmpty();
	}

	@Override
	@Transactional
	public boolean saveUser(User user) {
		if(checkUsernameExists(user)) {
			userEao.saveUser(user);
			
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userEao.saveUser(user);
	}

	@Override
	@Transactional
	public User verifyUser(User user) {
		List<User> userList = userEao.getUser(user.getUserName(), user.getUserPassword());
		
		if(userList.isEmpty()) {
			return null;
		}
		else {
			return userList.get(0);
		}
	}

	@Override
	@Transactional
	public User getUserAndMovies(User user) {
		List<User> userList = userEao.getUserAndMovies(user.getUserName());
		
		if(userList.isEmpty()) {
			return null;
		}
		else {
			return userList.get(0);
		}
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return userEao.getAllUsers();
	}

	@Override
	@Transactional
	public boolean addMovieToUser(User user, Movie movie) {
		List<Movie> userMovies = user.getMovies();
		
		if(userMovies.contains(movie)) {
			return true;
		}
		else {
			userMovies.add(movie);
			user.setMovies(userMovies);
			updateUser(user);
			
			return false;
		}
	}

	@Override
	public boolean verifyPasswords(String password1, String password2) {
		return password1.equals(password2);
	}
}
