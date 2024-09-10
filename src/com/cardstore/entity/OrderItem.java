package com.cardstore.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderItem")
@NamedQueries({ @NamedQuery(name = "OrderItem.findAll", query = "SELECT oi from OrderItem oi ORDER BY oi.orderItemId"),
		@NamedQuery(name = "OrderItem.countAll", query = "SELECT Count(*) FROM OrderItem oi"),
		@NamedQuery(name = "OrderItem.findByOrderId", query = "SELECT oi FROM OrderItem oi JOIN FETCH oi.listing WHERE oi.orderId = :orderId") })

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer orderItemId;
	private Integer orderId;
	private Integer quantity;
	
	private Listing listing;

	public OrderItem() {

	}

	public OrderItem(Integer orderItemId, Integer orderId, Integer quantity, Listing listing) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.listing = listing;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderItemId", unique = true, nullable = false)
	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	@Column(name = "orderId", nullable = false)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "listingId", referencedColumnName = "listingId")
	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId
				+ ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, orderItemId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(orderId, other.orderId)
				&& Objects.equals(orderItemId, other.orderItemId) && Objects.equals(quantity, other.quantity);
	}

}
