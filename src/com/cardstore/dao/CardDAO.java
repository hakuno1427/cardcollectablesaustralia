package com.cardstore.dao;

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

	@Override
	public long count() {
		return super.countWithNamedQuery("Card.countAll");
	}

}
