package com.asith.gmdb.eao;

import java.time.Year;
import java.util.List;

import com.asith.gmdb.entity.Movie;

public interface MovieEao {

	void saveMovie(Movie movie);

	Movie getMovie(long movieId);

	List<Movie> getMovie(String movieName);

	List<Movie> getMovie(String movieName, Year year);

	List<Movie> getAllMovies();

	void deleteMovie(String movieName, Year year);

}
