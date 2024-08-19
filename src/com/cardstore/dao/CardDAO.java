package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Card;

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

	@Override
	public long count() {
		return super.countWithNamedQuery("Card.countAll");
	}

}
