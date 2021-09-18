package com.asith.gmdb.eao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asith.gmdb.entity.Genre;

@Repository
public class GenreEaoImpl implements GenreEao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveGenre(Genre genre) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(genre);
	}

	@Override
	public Genre getGenre(int genreId) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Genre.class, genreId);
	}

	@Override
	public List<Genre> getGenre(String genreName) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
		
		Root<Genre> genre = cq.from(Genre.class);
		Predicate genreNamePredicate = cb.equal(genre.get("genreName"), genreName);
		cq.where(genreNamePredicate);
		
		TypedQuery<Genre> query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public List<Genre> getAllGenres() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Genre> cq = cb.createQuery(Genre.class);
		
		Root<Genre> genre = cq.from(Genre.class);
		CriteriaQuery<Genre> all = cq.select(genre).orderBy(cb.asc(genre.get("genreName")));
		
		TypedQuery<Genre> query = session.createQuery(all);
		
		return query.getResultList();
	}

	@Override
	public void deleteGenre(String genreName) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<Genre> cd = cb.createCriteriaDelete(Genre.class);
		
		Root<Genre> genre = cd.from(Genre.class);
		Predicate genreNamePredicate = cb.equal(genre.get("genreName"), genreName);
		cd.where(genreNamePredicate);
		
		session.createQuery(cd).executeUpdate();
	}
}
