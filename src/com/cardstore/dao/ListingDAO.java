package com.cardstore.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cardstore.entity.Listing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * @author Sera Jeong 12211242 Created Date: 18/08/2024
 */

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

	/* Retrieves the latest 4 listings with card details from the database */
	public List<Listing> listNewListingsWithCardDetails() {
		EntityManager entityManager = getEntityManager();
		List<Listing> listNewListings = new ArrayList<>();

		try {
			Query query = entityManager.createNamedQuery("Listing.listNew");
			query.setMaxResults(4);

			listNewListings = query.getResultList();
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to retrieve the latest listings.", e);
			throw new RuntimeException("Unable to retrieve latest listings", e);
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		logger.info("NEW LISTINGS QUERY EXECUTED");
		return listNewListings;
	}
	
	//rutvi
		public Listing findBySerialNumber(String serialNumber) {
			List<Listing> result = super.findWithNamedQuery("Listing.findBySerialNumber", "serialNumber", serialNumber);
			if (!result.isEmpty()) {
				return result.get(0);
			}
			return null;
		}
	
	public List<Listing> listSellerListing(Integer sellerId, int start, int pageSize){
		EntityManager entityManager = getEntityManager();
		List<Listing> sellerListing = new ArrayList<>();

		try {
			Query query = entityManager.createNamedQuery("Listing.listSellerListings", Listing.class);
			query.setParameter("sellerId", sellerId);
			query.setFirstResult(start);
			query.setMaxResults(pageSize);
			List<Listing> resultList = query.getResultList();
			logger.info("Successfully retrieved listings for seller ID: "+sellerId);
			return resultList;
		}catch (PersistenceException e){
			logger.log(Level.SEVERE,"Error retrieving listings for seller ID: "+sellerId, e);
			throw new RuntimeException("Error retrieving listings for seller ID: "+sellerId, e);
		}catch (Exception e){
			logger.log(Level.SEVERE,"Error retrieving listings for seller ID: "+sellerId, e);
			throw new RuntimeException("Error retrieving listings for seller ID: "+sellerId, e);
		}finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}

	}
	
	public long listingCountBySeller(Integer sellerId) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Long> query = entityManager.createNamedQuery("Listing.countBySellerId", Long.class);
		query.setParameter("sellerId", sellerId);
		return query.getSingleResult();
	}


}
