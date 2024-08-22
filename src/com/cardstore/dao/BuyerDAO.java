package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Buyer;

/**
 * @author Sera Jeong 12211242
 * Created Date: 18/08/2024
 */

public class BuyerDAO extends JpaDAO<Buyer> implements GenericDAO<Buyer> {
	public BuyerDAO() {
	}
	
	public Buyer create(Buyer buyer) {
		return super.create(buyer);
	}
	
	@Override
	public Buyer update(Buyer buyer) {
		return super.update(buyer);
	}
	
	@Override
	public Buyer get(Object buyerId) {
		return super.find(Buyer.class, buyerId);
	}

	@Override
	public void delete(Object buyerId) {
		super.delete(Buyer.class, buyerId);	
	}

	@Override
	public List<Buyer> listAll() {
		return super.findWithNamedQuery("Buyer.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Buyer.countAll");
	}

}
