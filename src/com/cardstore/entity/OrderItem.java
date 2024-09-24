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
@Table(name = "orderItem")
@NamedQueries({ @NamedQuery(name = "OrderItem.findAll", query = "SELECT oi from OrderItem oi ORDER BY oi.orderItemId"),
		@NamedQuery(name = "OrderItem.countAll", query = "SELECT Count(*) FROM OrderItem oi") })

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer orderItemId;
	private Integer listingId;
	private Integer orderId;
	private Integer quantity;

	public OrderItem() {

	}

	public OrderItem(Integer orderItemId, Integer listingId, Integer orderId, Integer quantity) {
		super();
		this.orderItemId = orderItemId;
		this.listingId = listingId;
		this.orderId = orderId;
		this.quantity = quantity;
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

	@Column(name = "listingId", nullable = false)
	public Integer getListingId() {
		return listingId;
	}

	public void setListingId(Integer listingId) {
		this.listingId = listingId;
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

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", listingId=" + listingId + ", orderId=" + orderId
				+ ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(listingId, orderId, orderItemId, quantity);
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
		return Objects.equals(listingId, other.listingId) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(orderItemId, other.orderItemId) && Objects.equals(quantity, other.quantity);
	}

}
