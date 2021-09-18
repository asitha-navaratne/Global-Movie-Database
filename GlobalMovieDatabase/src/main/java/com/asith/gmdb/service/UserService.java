package com.asith.gmdb.service;

import java.util.List;

import com.asith.gmdb.entity.Movie;
import com.asith.gmdb.entity.User;

public interface UserService {

	boolean checkUsernameExists(User user);

	boolean saveUser(User user);

	void updateUser(User user);

	User verifyUser(User user);

	User getUserAndMovies(User user);

	List<User> getAllUsers();

	boolean addMovieToUser(User user, Movie movie);

	boolean verifyPasswords(String password1, String password2);

}
