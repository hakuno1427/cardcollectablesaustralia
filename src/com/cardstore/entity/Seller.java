package com.cardstore.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seller")
public class Seller implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer sellerId;
	private String sellerEmail;

	public Seller() {

	}

	public Seller(Integer sellerId, String sellerEmail) {
		super();
		this.sellerId = sellerId;
		this.sellerEmail = sellerEmail;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sellerId", unique = true, nullable = false)
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "sellerEmail", nullable = false, length = 320)
	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", sellerEmail=" + sellerEmail + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(sellerEmail, sellerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(sellerEmail, other.sellerEmail) && Objects.equals(sellerId, other.sellerId);
	}

}
