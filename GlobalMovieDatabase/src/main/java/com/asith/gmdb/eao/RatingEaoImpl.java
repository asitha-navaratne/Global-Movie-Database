package com.asith.gmdb.eao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asith.gmdb.entity.Rating;

@Repository
public class RatingEaoImpl implements RatingEao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveRating(Rating rating) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(rating);
	}

	@Override
	public List<Rating> getRating(long userId, long movieId) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Rating> cq = cb.createQuery(Rating.class);
		
		Root<Rating> rating = cq.from(Rating.class);
		rating.fetch("user", JoinType.LEFT);
		rating.fetch("movie", JoinType.LEFT);
		Predicate userPredicate = cb.equal(rating.get("user").get("userId"), userId);
		Predicate moviePredicate = cb.equal(rating.get("movie").get("movieId"), movieId);
		cq.where(userPredicate, moviePredicate);
		
		TypedQuery<Rating> query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public void deleteRating(Rating rating) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(rating);
	}
}
