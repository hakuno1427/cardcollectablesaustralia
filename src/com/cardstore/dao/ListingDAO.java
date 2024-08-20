package com.cardstore.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cardstore.entity.Listing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ListingDAO extends JpaDAO<Listing> implements GenericDAO<Listing> {
	private static final Logger logger = Logger.getLogger(ListingDAO.class.getName());
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
	
	/*Retrieves the latest 4 listings from the database */
	public List<Listing> listNewListings(){
		EntityManager entityManager = getEntityManager();
		List<Listing> listings = null;
		
		try {
			Query query = entityManager.createNamedQuery("Listing.listNew");
			query.setFirstResult(0);
			query.setMaxResults(4);
			listings = query.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to retrieve the latest listings.", e);
            throw new RuntimeException("Unable to retrieve latest listings", e);
		} finally {
			if(entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return listings;
	}

}
