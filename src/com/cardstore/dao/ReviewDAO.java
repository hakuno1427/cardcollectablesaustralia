package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Card;
import com.cardstore.entity.Review;

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

	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countAll");
	}

}
