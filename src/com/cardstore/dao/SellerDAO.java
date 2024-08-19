package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Seller;

public class SellerDAO extends JpaDAO<Seller> implements GenericDAO<Seller> {
	public SellerDAO() {
	}
	
	public Seller create(Seller seller) {
		return super.create(seller);
	}
	
	@Override
	public Seller update(Seller seller) {
		return super.update(seller);
	}
	
	@Override
	public Seller get(Object sellerId) {
		return super.find(Seller.class, sellerId);
	}

	@Override
	public void delete(Object sellerId) {
		super.delete(Seller.class, sellerId);	
	}

	@Override
	public List<Seller> listAll() {
		return super.findWithNamedQuery("Seller.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Seller.countAll");
	}

}
