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
@Table(name = "paymentAccount")
@NamedQueries({
		@NamedQuery(name = "PaymentAccount.findAll", query = "SELECT pa from PaymentAccount pa ORDER BY pa.paymentAccountId"),
		@NamedQuery(name = "PaymentAccount.countAll", query = "SELECT Count(*) FROM PaymentAccount pa") })

public class PaymentAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer paymentAccountId;
	private Integer paymentServiceId;
	private Integer sellerId;
	private String accountDetails;

	public PaymentAccount() {

	}

	public PaymentAccount(Integer paymentAccountId, Integer paymentServiceId, Integer sellerId, String accountDetails) {
		super();
		this.paymentAccountId = paymentAccountId;
		this.paymentServiceId = paymentServiceId;
		this.sellerId = sellerId;
		this.accountDetails = accountDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentAccountId", unique = true, nullable = false)
	public Integer getPaymentAccountId() {
		return paymentAccountId;
	}

	public void setPaymentAccountId(Integer paymentAccountId) {
		this.paymentAccountId = paymentAccountId;
	}

	@Column(name = "paymentServiceId", nullable = false)
	public Integer getPaymentServiceId() {
		return paymentServiceId;
	}

	public void setPaymentServiceId(Integer paymentServiceId) {
		this.paymentServiceId = paymentServiceId;
	}

	@Column(name = "sellerId", nullable = false)
	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "accountDetails", nullable = false, length = 50)
	public String getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(String accountDetails) {
		this.accountDetails = accountDetails;
	}

	@Override
	public String toString() {
		return "PaymentAccount [paymentAccountId=" + paymentAccountId + ", paymentServiceId=" + paymentServiceId
				+ ", sellerId=" + sellerId + ", accountDetails=" + accountDetails + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountDetails, paymentAccountId, paymentServiceId, sellerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentAccount other = (PaymentAccount) obj;
		return Objects.equals(accountDetails, other.accountDetails)
				&& Objects.equals(paymentAccountId, other.paymentAccountId)
				&& Objects.equals(paymentServiceId, other.paymentServiceId) && Objects.equals(sellerId, other.sellerId);
	}

}
