package com.cardstore.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "listing")
@NamedQueries({ @NamedQuery(name = "Listing.findAll", query = "SELECT l from Listing l ORDER BY l.listingId"),
		@NamedQuery(name = "Listing.countAll", query = "SELECT Count(*) FROM Listing l"),
		@NamedQuery(name = "Listing.listNew", query = "SELECT l FROM Listing l ORDER BY l.listingId DESC"),
		@NamedQuery(name = "Listing.findBySerialNumber", query = "SELECT l FROM Listing l WHERE l.card.serialNumber = :serialNumber"),
		@NamedQuery(name = "Listing.listSellerListings", query = "SELECT l FROM Listing l where l.seller.userId = :sellerId"),
		@NamedQuery(name = "Listing.countBySellerId", query = "SELECT COUNT(*) FROM Listing l WHERE l.seller.userId = :sellerId")
})

public class Listing implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer listingId;
	private String condition;
	private double price;
	private Integer quantity;
	private User seller;
	private Card card;

	public Listing() {

	}

	public Listing(Integer listingId, User seller, Card card, String condition, double price,
			Integer quantity) {
		super();
		this.listingId = listingId;
		this.seller = seller;
		this.card = card;
		this.condition = condition;
		this.price = price;
		this.quantity = quantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "listingId", unique = true, nullable = false)
	public Integer getListingId() {
		return listingId;
	}

	public void setListingId(Integer listingId) {
		this.listingId = listingId;
	}

	@Column(name = "listingCondition", nullable = false, length = 50)
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "price", nullable = false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Listing [listingId=" + listingId + ", sellerId=" + seller.getUserId() + ", serialNumber=" + card.getSerialNumber()
				+ ", condition=" + condition + ", price=" + price + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(condition, listingId, price, quantity, seller.getUserId(), card.getSerialNumber());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Listing other = (Listing) obj;
		return Objects.equals(condition, other.condition) && Objects.equals(listingId, other.listingId)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(seller.getUserId(), other.seller.getUserId())
				&& Objects.equals(card.getSerialNumber(), other.card.getSerialNumber());
	}
	
	@ManyToOne
	@JoinColumn(name = "sellerId", nullable = false)
	public User getSeller() {
		return seller;
	}
	
	public void setSeller(User seller) {
		this.seller = seller;
	}
	

	@ManyToOne
	@JoinColumn(name = "serialNumber", nullable = false)
	public Card getCard() {
		return card;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
}
