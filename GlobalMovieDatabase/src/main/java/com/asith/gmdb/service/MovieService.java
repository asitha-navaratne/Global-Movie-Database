package com.asith.gmdb.service;

import java.time.Year;
import java.util.List;

import com.asith.gmdb.entity.Movie;

public interface MovieService {

	boolean checkMovieExists(Movie movie);

	boolean saveMovie(Movie movie);

	Movie getMovie(long movieId);

	Movie getMovie(String movieName, Year movieYear);

	List<Movie> getMovies(Movie movie);

	List<Movie> getAllMovies();

	boolean deleteMovie(Movie movie);

}
