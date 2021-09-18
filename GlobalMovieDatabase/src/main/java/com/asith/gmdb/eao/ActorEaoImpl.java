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

import com.asith.gmdb.entity.Actor;

@Repository
public class ActorEaoImpl implements ActorEao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveActor(Actor actor) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(actor);
	}

	@Override
	public Actor getActor(long actorId) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Actor.class, actorId);
	}

	@Override
	public List<Actor> getActor(String firstName, String lastName) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Actor> cq = cb.createQuery(Actor.class);
		
		Root<Actor> actor = cq.from(Actor.class);
		Predicate actorFirstNamePredicate = cb.equal(actor.get("actorFirstName"), firstName);
		Predicate actorLastNamePredicate = cb.equal(actor.get("actorLastName"), lastName);
		cq.where(actorFirstNamePredicate, actorLastNamePredicate);
		
		TypedQuery<Actor> query = session.createQuery(cq);
		
		return query.getResultList();
	}

	@Override
	public List<Actor> getAllActors() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Actor> cq = cb.createQuery(Actor.class);
		
		Root<Actor> actor = cq.from(Actor.class);
		CriteriaQuery<Actor> all = cq.select(actor).orderBy(cb.asc(actor.get("actorFirstName")));
		
		TypedQuery<Actor> query = session.createQuery(all);
		
		return query.getResultList();
	}

	@Override
	public void deleteActor(String firstName, String lastName) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<Actor> cd = cb.createCriteriaDelete(Actor.class);
		
		Root<Actor> actor = cd.from(Actor.class);
		Predicate actorFirstNamePredicate = cb.equal(actor.get("actorFirstName"), firstName);
		Predicate actorLastNamePredicate = cb.equal(actor.get("actorLastName"), lastName);
		cd.where(actorFirstNamePredicate, actorLastNamePredicate);
		
		session.createQuery(cd).executeUpdate();
	}
}
