package com.cardstore.dao;

import java.util.ArrayList;
import java.util.List;

import com.cardstore.entity.Card;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * @author Sera Jeong 12211242 Created Date: 17/08/2024
 */

public class CardDAO extends JpaDAO<Card> implements GenericDAO<Card> {

	public CardDAO() {
	}

	public Card create(Card card) {
		return super.create(card);
	}

	@Override
	public Card update(Card card) {
		return super.update(card);
	}

	@Override
	public Card get(Object serialNumber) {
		return super.find(Card.class, serialNumber);
	}

	@Override
	public void delete(Object serialNumber) {
		super.delete(Card.class, serialNumber);
	}

	@Override
	public List<Card> listAll() {
		return super.findWithNamedQuery("Card.findAll");
	}

	public List<Card> listPaged(int start, int pageSize) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Card> query = entityManager.createNamedQuery("Card.findAll", Card.class);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public List<Card> search(String keyword){
		return super.findWithNamedQuery("Card.search", "keyword", keyword);
	}
	
	public List<Card> searchPaged(String keyword, int start, int pageSize){
		EntityManager entityManager = getEntityManager();
	    TypedQuery<Card> query = entityManager.createNamedQuery("Card.search", Card.class);
	    query.setParameter("keyword", keyword);
	    query.setFirstResult(start);
	    query.setMaxResults(pageSize);
	    return query.getResultList();
	}
	
	public long countSearchResults(String keyword) {
	    EntityManager entityManager = getEntityManager();
	    TypedQuery<Long> query = entityManager.createNamedQuery("Card.countSearchResults", Long.class);
	    query.setParameter("keyword", keyword);
	    return query.getSingleResult();
	}
	
	public List<Card> getCardsWithListings(int limit) {
	    EntityManager entityManager = getEntityManager();
	    List<Card> cards = new ArrayList<>();

	    try {
	        String jpql = "SELECT DISTINCT c FROM Card c JOIN c.listings l WHERE l.quantity > 0 ORDER BY FUNCTION('RAND')";
	        TypedQuery<Card> query = entityManager.createQuery(jpql, Card.class);
	        query.setMaxResults(limit); 

	        cards = query.getResultList();
	        
	    } catch (Exception e) {
	        throw new RuntimeException("Unable to retrieve cards with listings", e);
	    } finally {
	        if (entityManager != null && entityManager.isOpen()) {
	            entityManager.close();
	        }
	    }
	    return cards;
	}
	
	public double getLowestPriceForCard(String serialNumber) {
	    EntityManager entityManager = getEntityManager();
	    double lowestPrice = 0.0;

	    try {
	        String jpql = "SELECT MIN(l.price) FROM Listing l WHERE l.card.serialNumber = :serialNumber AND l.quantity > 0";
	        TypedQuery<Double> query = entityManager.createQuery(jpql, Double.class);
	        query.setParameter("serialNumber", serialNumber);
	        lowestPrice = query.getSingleResult();

	    } catch (Exception e) {
	        throw new RuntimeException("Unable to retrieve the lowest price for card", e);
	    } finally {
	        if (entityManager != null && entityManager.isOpen()) {
	            entityManager.close();
	        }
	    }
	    return lowestPrice;
	}

	
	@Override
	public long count() {
		return super.countWithNamedQuery("Card.countAll");
	}
	
	public List<Card> fetchCardsWithLazyLoading(int offset, int limit){
		EntityManager entityManager = getEntityManager();
		String jpql = "SELECT c FROM Card c";
        TypedQuery<Card> query = entityManager.createQuery(jpql, Card.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
	}

}
