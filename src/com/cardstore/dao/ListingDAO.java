package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Listing;

public class ListingDAO extends JpaDAO<Listing> implements GenericDAO<Listing> {
	public ListingDAO() {
	}
	
	public Listing create(Listing listing) {
		return super.create(listing);
	}
	
	@Override
	public Listing update(Listing listing) {
		return super.update(listing);
	}
	
	@Override
	public Listing get(Object listingId) {
		return super.find(Listing.class, listingId);
	}

	@Override
	public void delete(Object listingId) {
		super.delete(Listing.class, listingId);	
	}

	@Override
	public List<Listing> listAll() {
		return super.findWithNamedQuery("Listing.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Listing.countAll");
	}

}
