package com.asith.gmdb.eao;

import java.util.List;

import com.asith.gmdb.entity.User;

public interface UserEao {

	void saveUser(User user);

	List<User> getUser(String username);

	List<User> getUser(String username, String password);

	List<User> getUserAndMovies(String username);

	List<User> getAllUsers();

}
