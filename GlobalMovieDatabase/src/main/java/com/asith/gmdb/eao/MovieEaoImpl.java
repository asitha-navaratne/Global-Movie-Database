package com.asith.gmdb.eao;

import java.time.Year;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asith.gmdb.entity.Movie;

@Repository
public class MovieEaoImpl implements MovieEao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveMovie(Movie movie) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(movie);
	}

	@Override
	public Movie getMovie(long movieId) {
		Session session = sessionFactory.getCurrentSession();
		Movie movie = session.get(Movie.class, movieId);
		
		return movie;
	}

	@Override
	public List<Movie> getMovie(String movieName) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
		
		Root<Movie> movie = cq.from(Movie.class);
		movie.fetch("genres", JoinType.LEFT);
		Predicate movieNamePredicate = cb.equal(movie.get("movieName"), movieName);
		cq.where(movieNamePredicate).distinct(true);
		
		TypedQuery<Movie> query = session.createQuery(cq).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		List<Movie> movieList =  query.getResultList();
		
		cq = cb.createQuery(Movie.class);
		
		movie = cq.from(Movie.class);
		movie.fetch("actors", JoinType.LEFT);
		cq.where(movieNamePredicate).distinct(true);
		
		query = session.createQuery(cq).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		movieList = query.getResultList();
		
		cq = cb.createQuery(Movie.class);
		
		movie = cq.from(Movie.class);
		movie.fetch("ratings", JoinType.LEFT);
		cq.where(movieNamePredicate).distinct(true);
		
		query = session.createQuery(cq).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		movieList = query.getResultList();
		
		return movieList;
	}

	@Override
	public List<Movie> getMovie(String movieName, Year year) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
		
		Root<Movie> movie = cq.from(Movie.class);
		movie.fetch("genres", JoinType.LEFT);
		Predicate movieNamePredicate = cb.equal(movie.get("movieName"), movieName);
		Predicate movieYearPredicate = cb.equal(movie.get("movieYear"), year);
		cq.where(movieNamePredicate, movieYearPredicate).distinct(true);
		
		TypedQuery<Movie> query = session.createQuery(cq).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		List<Movie> movieList =  query.getResultList();
		
		cq = cb.createQuery(Movie.class);
		
		movie = cq.from(Movie.class);
		movie.fetch("directors", JoinType.LEFT);
		cq.where(movieNamePredicate, movieYearPredicate).distinct(true);
		
		query = session.createQuery(cq).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		movieList = query.getResultList();
		
		cq = cb.createQuery(Movie.class);
		
		movie = cq.from(Movie.class);
		movie.fetch("actors", JoinType.LEFT);
		cq.where(movieNamePredicate, movieYearPredicate).distinct(true);
		
		query = session.createQuery(cq).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		movieList = query.getResultList();
		
		cq = cb.createQuery(Movie.class);
		
		movie = cq.from(Movie.class);
		movie.fetch("ratings", JoinType.LEFT);
		cq.where(movieNamePredicate, movieYearPredicate).distinct(true);
		
		query = session.createQuery(cq).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		movieList = query.getResultList();
		
		return movieList;
	}

	@Override
	public List<Movie> getAllMovies() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
		
		Root<Movie> movie = cq.from(Movie.class);
		CriteriaQuery<Movie> all = cq.select(movie).orderBy(cb.asc(movie.get("movieName")));
		
		TypedQuery<Movie> query = session.createQuery(all);
		
		return query.getResultList();
	}

	@Override
	public void deleteMovie(String movieName, Year year) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<Movie> cd = cb.createCriteriaDelete(Movie.class);
		
		Root<Movie> movie = cd.from(Movie.class);
		Predicate movieNamePredicate = cb.equal(movie.get("movieName"), movieName);
		Predicate movieYearPredicate = cb.equal(movie.get("movieYear"), year);
		cd.where(movieNamePredicate, movieYearPredicate);
		
		session.createQuery(cd).executeUpdate();
	}
}
