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
@Table(name="buyer")
@NamedQueries({
	@NamedQuery(name = "Buyer.findAll", query = "SELECT b from Buyer b ORDER BY b.buyerId"),	
	@NamedQuery(name = "Buyer.countAll", query = "SELECT Count(*) FROM Buyer b")	
})

public class Buyer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer buyerId;
	private String buyerEmail;

	public Buyer() {
		
	}
	
	public Buyer(Integer buyerId, String buyerEmail) {
		super();
		this.buyerId = buyerId;
		this.buyerEmail = buyerEmail;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="buyerId", unique=true, nullable=false)
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name="buyerEmail", nullable=false, length=320)
	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	@Override
	public String toString() {
		return "Buyer [buyerId=" + buyerId + ", buyerEmail=" + buyerEmail + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyerEmail, buyerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buyer other = (Buyer) obj;
		return Objects.equals(buyerEmail, other.buyerEmail) && Objects.equals(buyerId, other.buyerId);
	}

}
