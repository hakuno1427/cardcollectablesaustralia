package com.cardstore.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="listing")
@NamedQueries({
	@NamedQuery(name = "Listing.findAll", query = "SELECT l from Listing l ORDER BY l.listingId"),
	@NamedQuery(name = "Listing.countAll", query = "SELECT Count(*) FROM Listing l"),
	@NamedQuery(name = "Listing.listNewWithCardDetails", query = "SELECT l.listingId, l.serialNumber, l.condition, l.price, l.quantity, l.sellerId, c.cardName, c.marketprice, c.imageUrl FROM Listing l JOIN Card c ON l.serialNumber = c.serialNumber ORDER BY l.listingId DESC")
})

public class Listing implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer listingId;
	private Integer sellerId;
	private String serialNumber;
	private String condition;
	private double price;
	private Integer quantity;
	
	@Transient
	private String cardName;
	@Transient
	private double marketPrice;
	@Transient
	private String imageUrl;

	public Listing() {

	}

	public Listing(Integer listingId, Integer sellerId, String serialNumber, String condition, double price,
			Integer quantity) {
		super();
		this.listingId = listingId;
		this.sellerId = sellerId;
		this.serialNumber = serialNumber;
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

	@Column(name = "sellerId", nullable = false)
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "serialNumber", nullable = false, length = 50)
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
		return "Listing [listingId=" + listingId + ", sellerId=" + sellerId + ", serialNumber=" + serialNumber
				+ ", condition=" + condition + ", price=" + price + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(condition, listingId, price, quantity, sellerId, serialNumber);
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
				&& Objects.equals(quantity, other.quantity) && Objects.equals(sellerId, other.sellerId)
				&& Objects.equals(serialNumber, other.serialNumber);
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
