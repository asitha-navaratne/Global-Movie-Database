package com.asith.gmdb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asith.gmdb.eao.DirectorEao;
import com.asith.gmdb.entity.Director;

@Service
public class DirectorServiceImpl implements DirectorService {

	@Autowired
	private DirectorEao directorEao;

	@Override
	@Transactional
	public boolean checkDirectorExists(Director director) {
		List<Director> directorList = directorEao.getDirector(director.getDirectorFirstName().toUpperCase(), director.getDirectorLastName().toUpperCase());
		
		return directorList.isEmpty();
	}

	@Override
	@Transactional
	public boolean saveDirector(Director director) {
		if(checkDirectorExists(director)) {
			String directorFirstName = director.getDirectorFirstName().toUpperCase();
			String directorLastName = director.getDirectorLastName().toUpperCase();
			
			director.setDirectorFirstName(directorFirstName);
			director.setDirectorLastName(directorLastName);
			directorEao.saveDirector(director);
			
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	@Transactional
	public List<Director> getDirectorsFromList(String directorList) {
		List<String> stringList = Arrays.asList(directorList.split(","));
		List<Director> directors = new ArrayList<>();
		
		Director director;
		
		for(String id : stringList) {
			director = directorEao.getDirector(Long.parseLong(id));
			directors.add(director);
		}
		
		return directors;
	}

	@Override
	@Transactional
	public List<Director> getAllDirectors(){
		List<Director> directorList = directorEao.getAllDirectors();
		
		return directorList;
	}

	@Override
	@Transactional
	public boolean deleteDirector(Director director) {
		if(checkDirectorExists(director)) {
			return false;
		}
		else {
			directorEao.deleteDirector(director.getDirectorFirstName().toUpperCase(), director.getDirectorLastName().toUpperCase());
			
			return true;
		}
	}
}
