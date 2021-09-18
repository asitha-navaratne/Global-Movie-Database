package com.asith.gmdb.eao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asith.gmdb.entity.Admin;

@Repository
public class AdminEaoImpl implements AdminEao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(admin);
	}

	@Override
	public Admin getAdmin() {
		Session session = sessionFactory.getCurrentSession();
		Admin admin = session.get(Admin.class, 101);
		
		return admin;
	}

	@Override
	public List<Admin> getAdmin(String password) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
		
		Root<Admin> admin = cq.from(Admin.class);
		Predicate passwordPredicate = cb.equal(admin.get("adminPassword"), password);
		cq.where(passwordPredicate);
		
		TypedQuery<Admin> query = session.createQuery(cq);
		
		return query.getResultList();
	}
}
