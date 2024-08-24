package com.cardstore.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cardstore.entity.Listing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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
			Query query = entityManager.createNamedQuery("Listing.listNewWithCardDetails");
			query.setMaxResults(4);

			List<Object[]> results = query.getResultList();

			for (Object[] row : results) {
				Listing listing = new Listing();
				listing.setListingId((Integer) row[0]);
				listing.setSerialNumber((String) row[1]);
				listing.setCondition((String) row[2]);
				listing.setPrice((Double) row[3]);
				listing.setQuantity((Integer) row[4]);
				listing.setSellerId((Integer) row[5]);

				listing.setCardName((String) row[6]);
				listing.setMarketPrice((Double) row[7]);
				listing.setImageUrl((String) row[8]);

				listNewListings.add(listing);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to retrieve the latest listings.", e);
			throw new RuntimeException("Unable to retrieve latest listings", e);
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
		}
		return listNewListings;
	}

}
