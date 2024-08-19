package com.cardstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.cardstore.entity.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

//provide common operations that are shared among subclasses
public class JpaDAO<E> {
	private static EntityManagerFactory entityManagerFactory;

	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("CardCollectablesAustralia");
	}

	public JpaDAO() {
	}

	// create
	public E create(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);

		entityManager.getTransaction().commit();
		return entity;
	}

	// update
	public E update(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();

		entityManager.close();
		return entity;
	}

	// find
	public E find(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		E entity = entityManager.find(type, id);

		if (entity != null) {
			entityManager.refresh(entity);
			;
		}

		entityManager.close();
		return entity;
	}

	// delete
	public void delete(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);

		entityManager.getTransaction().commit();
	}

	// find
	public List<E> findWithNamedQuery(String queryName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		List<E> result = query.getResultList();

		entityManager.close();
		return result;
	}
	
	public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		
		query.setParameter(paramName, paramValue);
		
		List<E> result = query.getResultList();
		
		entityManager.close();
		
		return result;
	}
	

	public long countWithNamedQuery(String queryName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(queryName);
		long result = (long) query.getSingleResult();

		entityManager.close();
		return result;
	}

	public List<E> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		
		Set<Entry<String, Object>> setParameters = parameters.entrySet();
		
		for (Entry<String, Object> entry : setParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<E> result = query.getResultList();
		
		entityManager.close();
		
		return result;
	}
}
