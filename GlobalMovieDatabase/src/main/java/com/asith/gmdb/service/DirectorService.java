package com.asith.gmdb.service;

import java.util.List;

import com.asith.gmdb.entity.Director;

public interface DirectorService {

	boolean checkDirectorExists(Director director);

	boolean saveDirector(Director director);

	List<Director> getDirectorsFromList(String directorList);

	List<Director> getAllDirectors();

	boolean deleteDirector(Director director);

}
