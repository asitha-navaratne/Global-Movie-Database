package com.asith.gmdb.eao;

import java.util.List;

import com.asith.gmdb.entity.Genre;

public interface GenreEao {

	void saveGenre(Genre genre);

	Genre getGenre(int genreId);

	List<Genre> getGenre(String genreName);

	List<Genre> getAllGenres();

	void deleteGenre(String genreName);

}
