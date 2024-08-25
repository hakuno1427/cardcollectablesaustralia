package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * @author Sera Jeong 12211242 Created Date: 18/08/2024
 */

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {
	public ReviewDAO() {
	}

	public Review create(Review review) {
		return super.create(review);
	}

	@Override
	public Review update(Review review) {
		return super.update(review);
	}

	@Override
	public Review get(Object reviewId) {
		return super.find(Review.class, reviewId);
	}

	@Override
	public void delete(Object reviewId) {
		super.delete(Review.class, reviewId);
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.findAll");
	}
	
    public List<Review> listPaged(int start, int pageSize) {
        EntityManager entityManager = getEntityManager();
        TypedQuery<Review> query = entityManager.createNamedQuery("Review.findAll", Review.class);
        query.setFirstResult(start);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countAll");
	}

}
