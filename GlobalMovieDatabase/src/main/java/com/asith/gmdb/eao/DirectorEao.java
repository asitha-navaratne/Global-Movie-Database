package com.asith.gmdb.eao;

import java.util.List;

import com.asith.gmdb.entity.Director;

public interface DirectorEao {

	void saveDirector(Director director);

	Director getDirector(long directorId);

	List<Director> getDirector(String firstName, String lastName);

	List<Director> getAllDirectors();

	void deleteDirector(String firstName, String lastName);

}
