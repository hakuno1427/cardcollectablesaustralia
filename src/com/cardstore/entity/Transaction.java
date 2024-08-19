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
@Table(name="transaction")
@NamedQueries({
	@NamedQuery(name = "Transaction.findAll", query = "SELECT t from Transaction t ORDER BY t.transactionId"),	
	@NamedQuery(name = "Transaction.countAll", query = "SELECT Count(*) FROM Transaction t")	
})

public class Transaction implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer transactionId;
	private Integer orderId;
	private Integer buyerId;
	private Integer sellerId;
	private Integer paymentServiceId;
	private Integer paymentAccountId;
	private LocalDate transactionDate;
	private String status;

	public Transaction() {
		
	}
	
	public Transaction(Integer transactionId, Integer orderId, Integer buyerId, Integer sellerId,
			Integer paymentServiceId, Integer paymentAccountId, LocalDate transactionDate, String status) {
		super();
		this.transactionId = transactionId;
		this.orderId = orderId;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.paymentServiceId = paymentServiceId;
		this.paymentAccountId = paymentAccountId;
		this.transactionDate = transactionDate;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transactionId", unique=true, nullable=false)
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name="orderId", nullable=false)
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name="buyerId", nullable=false)
	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@Column(name="sellerId", nullable=false)
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name="paymentServiceId", nullable=false)
	public Integer getPaymentServiceId() {
		return paymentServiceId;
	}

	public void setPaymentServiceId(Integer paymentServiceId) {
		this.paymentServiceId = paymentServiceId;
	}
	
	@Column(name="paymentAccountId", nullable=false)
	public Integer getPaymentAccountId() {
		return paymentAccountId;
	}

	public void setPaymentAccountId(Integer paymentAccountId) {
		this.paymentAccountId = paymentAccountId;
	}

	@Column(name="transactionDate", nullable=false)
	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Column(name="status", nullable=false, length=50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", orderId=" + orderId + ", buyerId=" + buyerId
				+ ", sellerId=" + sellerId + ", paymentServiceId=" + paymentServiceId + ", paymentAccountId="
				+ paymentAccountId + ", transactionDate=" + transactionDate + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyerId, orderId, paymentAccountId, paymentServiceId, sellerId, status, transactionDate,
				transactionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(paymentAccountId, other.paymentAccountId)
				&& Objects.equals(paymentServiceId, other.paymentServiceId) && Objects.equals(sellerId, other.sellerId)
				&& Objects.equals(status, other.status) && Objects.equals(transactionDate, other.transactionDate)
				&& Objects.equals(transactionId, other.transactionId);
	}

}
