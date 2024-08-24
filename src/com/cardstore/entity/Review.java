package com.cardstore.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
@NamedQueries({ @NamedQuery(name = "Review.findAll", query = "SELECT r from Review r ORDER BY r.reviewId"),
		@NamedQuery(name = "Review.countAll", query = "SELECT Count(*) FROM Review r") })

public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer reviewId;
	private Integer buyerId;
	private Integer sellerId;
	private double rating;
	private String comment;
	private LocalDate reviewDate;

	public Review() {

	}

	public Review(Integer reviewId, Integer buyerId, Integer sellerId, double rating, String comment,
			LocalDate reviewDate) {
		super();
		this.reviewId = reviewId;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.rating = rating;
		this.comment = comment;
		this.reviewDate = reviewDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reviewId", unique = true, nullable = false)
	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	@Column(name = "buyerId", nullable = false)
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name = "sellerId", nullable = false)
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "rating", nullable = false)
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Column(name = "comment", nullable = false, length = 300)
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "reviewDate", nullable = false)
	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", buyerId=" + buyerId + ", sellerId=" + sellerId + ", rating=" + rating
				+ ", comment=" + comment + ", reviewDate=" + reviewDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyerId, comment, rating, reviewDate, reviewId, sellerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(comment, other.comment)
				&& Double.doubleToLongBits(rating) == Double.doubleToLongBits(other.rating)
				&& Objects.equals(reviewDate, other.reviewDate) && Objects.equals(reviewId, other.reviewId)
				&& Objects.equals(sellerId, other.sellerId);
	}

}
