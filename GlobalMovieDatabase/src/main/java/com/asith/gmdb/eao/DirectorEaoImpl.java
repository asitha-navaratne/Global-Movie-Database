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

import com.asith.gmdb.entity.Director;

@Repository
public class DirectorEaoImpl implements DirectorEao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveDirector(Director director) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(director);
	}

	@Override
	public Director getDirector(long directorId) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Director.class, directorId);
	}

	@Override
	public List<Director> getDirector(String firstName, String lastName) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Director> cq = cb.createQuery(Director.class);
		
		Root<Director> director = cq.from(Director.class);
		Predicate directorFirstNamePredicate = cb.equal(director.get("directorFirstName"), firstName);
		Predicate directorLastNamePredicate = cb.equal(director.get("directorLastName"), lastName);
		cq.where(directorFirstNamePredicate, directorLastNamePredicate);
		
		TypedQuery<Director> query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public List<Director> getAllDirectors() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Director> cq = cb.createQuery(Director.class);
		
		Root<Director> director = cq.from(Director.class);
		CriteriaQuery<Director> all = cq.select(director).orderBy(cb.asc(director.get("directorFirstName")));
		
		TypedQuery<Director> query = session.createQuery(all);
		
		return query.getResultList();
	}

	@Override
	public void deleteDirector(String firstName, String lastName) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<Director> cd = cb.createCriteriaDelete(Director.class);
		
		Root<Director> director = cd.from(Director.class);
		Predicate directorFirstNamePredicate = cb.equal(director.get("directorFirstName"), firstName);
		Predicate directorLastNamePredicate = cb.equal(director.get("directorLastName"), lastName);
		cd.where(directorFirstNamePredicate, directorLastNamePredicate);
		
		session.createQuery(cd).executeUpdate();
	}
}
