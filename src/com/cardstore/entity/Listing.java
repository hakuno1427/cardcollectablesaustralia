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

@Entity
@Table(name="listing")
@NamedQueries({
	@NamedQuery(name = "Listing.findAll", query = "SELECT l from Listing l ORDER BY l.listingId"),	
	@NamedQuery(name = "Listing.coundAll", query = "SELECT Count(*) FROM Listing l")	
})

public class Listing implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer listingId;
	private Integer sellerId;
	private String serialNumber;
	private String condition;
	private double price;
	private Integer quantity;
	
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
	@Column(name="listingId", unique=true, nullable=false)
	public Integer getListingId() {
		return listingId;
	}

	public void setListingId(Integer listingId) {
		this.listingId = listingId;
	}

	@Column(name="sellerId", nullable=false)
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name="serialNumber", nullable=false, length=50)
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name="condition", nullable=false, length=50)
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name="price", nullable=false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="quantity", nullable=false)
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

}
