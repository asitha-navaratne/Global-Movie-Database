package com.asith.gmdb.service;

import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asith.gmdb.eao.MovieEao;
import com.asith.gmdb.entity.Movie;
import com.asith.gmdb.entity.Rating;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieEao movieEao;

	@Override
	@Transactional
	public boolean checkMovieExists(Movie movie) {
		List<Movie> movieList = movieEao.getMovie(movie.getMovieName(), movie.getMovieYear());
		
		return movieList.isEmpty();
	}

	@Override
	@Transactional
	public boolean saveMovie(Movie movie) {
		if(checkMovieExists(movie)) {
			movieEao.saveMovie(movie);
			
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional
	public Movie getMovie(long movieId) {
		Movie movie = movieEao.getMovie(movieId);
		
		return movie;
	}

	@Override
	@Transactional
	public Movie getMovie(String movieName, Year movieYear) {
		List<Movie> movieList = movieEao.getMovie(movieName, movieYear);
		
		Movie movie = movieList.get(0);
		
		float movieRating = 0;
		int ratings = 0;
		
		for(Rating r : movie.getRatings()) {
			movieRating += r.getUserRating();
			ratings++;
		}
		if(ratings != 0) {
			movieRating /= ratings;
			movie.setMovieRating(Math.round((movieRating*10.0)/10.0));
		}
		else {
			movie.setMovieRating(0);
		}
		
		return movie;
	}

	@Override
	@Transactional
	public List<Movie> getMovies(Movie movie) {
		List<Movie> movieList = movieEao.getMovie(movie.getMovieName());
		
		float movieRating = 0;
		int ratings = 0;
		
		for(Movie m : movieList) {
			for(Rating r : m.getRatings()) {
				movieRating += r.getUserRating();
				ratings++;
			}
			if(ratings != 0) {
				movieRating /= ratings;
				m.setMovieRating(Math.round((movieRating*10.0)/10.0));
			}
			else {
				m.setMovieRating(0);
			}
			movieRating = 0;
			ratings = 0;
		}
		
		return movieList;
	}

	@Override
	@Transactional
	public List<Movie> getAllMovies() {
		List<Movie> movieList = movieEao.getAllMovies();
		
		return movieList;
	}

	@Override
	@Transactional
	public boolean deleteMovie(Movie movie) {
		if(checkMovieExists(movie)) {
			return false;
		}
		else {
			movieEao.deleteMovie(movie.getMovieName(), movie.getMovieYear());
			
			return true;
		}
	}
}
