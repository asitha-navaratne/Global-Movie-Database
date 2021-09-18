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
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asith.gmdb.entity.User;

@Repository
public class UserEaoImpl implements UserEao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(user);
	}

	@Override
	public List<User> getUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		
		Root<User> user = cq.from(User.class);
		Predicate userNamePredicate = cb.equal(user.get("userName"), username);
		cq.where(userNamePredicate);
		
		TypedQuery<User> query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public List<User> getUser(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		
		Root<User> user = cq.from(User.class);
		Predicate userNamePredicate = cb.equal(user.get("userName"), username);
		Predicate passwordPredicate = cb.equal(user.get("userPassword"), password);
		cq.where(userNamePredicate, passwordPredicate);
		
		TypedQuery<User> query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public List<User> getUserAndMovies(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		
		Root<User> user = cq.from(User.class);
		user.fetch("movies", JoinType.LEFT);
		Predicate userNamePredicate = cb.equal(user.get("userName"), username);
		cq.where(userNamePredicate).distinct(true);
		
		TypedQuery<User> query = session.createQuery(cq).setHint(QueryHints.PASS_DISTINCT_THROUGH, false);
		
		return query.getResultList();
	}

	@Override
	public List<User> getAllUsers(){
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		
		Root<User> user = cq.from(User.class);
		CriteriaQuery<User> all = cq.select(user).orderBy(cb.asc(user.get("userName")));
		
		TypedQuery<User> query = session.createQuery(all);
		
		return query.getResultList();
	}
}
