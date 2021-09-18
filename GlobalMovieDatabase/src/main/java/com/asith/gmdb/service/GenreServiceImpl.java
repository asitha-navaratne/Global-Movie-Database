package com.asith.gmdb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asith.gmdb.eao.GenreEao;
import com.asith.gmdb.entity.Genre;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreEao genreEao;

	@Override
	@Transactional
	public boolean checkGenreExists(Genre genre) {
		List<Genre> genreList = genreEao.getGenre(genre.getGenreName().toUpperCase());
		
		return genreList.isEmpty();
	}

	@Override
	@Transactional
	public boolean saveGenre(Genre genre) {
		if(checkGenreExists(genre)) {
			String genreName = genre.getGenreName().toUpperCase();
			
			genre.setGenreName(genreName);
			genreEao.saveGenre(genre);
			
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional
	public List<Genre> getGenresFromList(String genreList) {
		List<String> stringList = Arrays.asList(genreList.split(","));
		List<Genre> genres = new ArrayList<>();
		
		Genre genre;
		
		for(String id : stringList) {
			genre = genreEao.getGenre(Integer.parseInt(id));
			genres.add(genre);
		}
		
		return genres;
	}

	@Override
	@Transactional
	public List<Genre> getAllGenres() {
		List<Genre> genreList = genreEao.getAllGenres();
		
		return genreList;
	}

	@Override
	@Transactional
	public boolean deleteGenre(Genre genre) {
		if(checkGenreExists(genre)) {
			return false;
		}
		else {
			genreEao.deleteGenre(genre.getGenreName().toUpperCase());
			
			return true;
		}
	}
}
