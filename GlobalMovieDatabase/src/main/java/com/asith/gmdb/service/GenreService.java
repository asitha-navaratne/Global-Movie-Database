package com.asith.gmdb.service;

import java.util.List;

import com.asith.gmdb.entity.Genre;

public interface GenreService {

	boolean checkGenreExists(Genre genre);

	boolean saveGenre(Genre genre);

	List<Genre> getGenresFromList(String genreList);

	List<Genre> getAllGenres();

	boolean deleteGenre(Genre genre);

}
