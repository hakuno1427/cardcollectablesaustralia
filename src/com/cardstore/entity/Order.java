package com.cardstore.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
@Table(name = "sales_order")
@NamedQueries({ @NamedQuery(name = "Order.findAll", query = "SELECT o from Order o ORDER BY o.orderId"),
		@NamedQuery(name = "Order.countAll", query = "SELECT Count(*) FROM Order o"),
		@NamedQuery(name = "Order.findByBuyerId", query = "SELECT o FROM Order o WHERE o.buyerId = :buyerId ORDER BY o.orderDate DESC") })

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String STATUS_SHIPMENT_PENDING = "shipment pending";
	public static final String STATUS_SHIPPED = "shipped";
	public static final String STATUS_COMPLETE = "complete";

	private Integer orderId;
	private Integer buyerId;
	private Integer sellerId;

	private double totalPrice;
	private String status;
	private LocalDate orderDate;
	private String shippingAddress;
	private String billingAddress;
	private String trackingNumber;

	@Transient
	private List<OrderItem> orderItems;

	public Order() {

	}

	public Order(Integer orderId, Integer buyerId, Integer sellerId, List<OrderItem> orderItems, double totalPrice, String status,
			LocalDate orderDate, String shippingAddress, String billingAddress, String trackingNumber) {
		super();
		this.orderId = orderId;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.orderItems = orderItems;
		this.totalPrice = totalPrice;
		this.status = status;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.trackingNumber = trackingNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId", unique = true, nullable = false)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	@Transient
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Column(name = "totalPrice", nullable = false)
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "status", nullable = false, length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "orderDate", nullable = false)
	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "shippingAddress", nullable = false, length = 500)
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Column(name = "billingAddress", length = 500)
	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	@Column(name = "trackingNumber", length = 50)
	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", buyerId=" + buyerId + ", orderItems=" + orderItems + ", totalPrice="
				+ totalPrice + ", status=" + status + ", orderDate=" + orderDate + ", shippingAddress="
				+ shippingAddress + ", billingAddress=" + billingAddress + ", trackingNumber=" + trackingNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(billingAddress, buyerId, orderDate, orderId, orderItems, shippingAddress, status,
				totalPrice, trackingNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(billingAddress, other.billingAddress) && Objects.equals(buyerId, other.buyerId)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(orderItems, other.orderItems)
				&& Objects.equals(shippingAddress, other.shippingAddress) && Objects.equals(status, other.status)
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice)
				&& Objects.equals(trackingNumber, other.trackingNumber);
	}

}
