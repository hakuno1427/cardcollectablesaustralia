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
@Table(name = "paymentService")
public class PaymentService implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer paymentServiceId;
	private String serviceName;

	public PaymentService() {

	}

	public PaymentService(Integer paymentServiceId, String serviceName) {
		super();
		this.paymentServiceId = paymentServiceId;
		this.serviceName = serviceName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentServiceId", unique = true, nullable = false)
	public Integer getPaymentServiceId() {
		return paymentServiceId;
	}

	public void setPaymentServiceId(Integer paymentServiceId) {
		this.paymentServiceId = paymentServiceId;
	}

	@Column(name = "serviceName", nullable = false, length = 50)
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "PaymentService [paymentServiceId=" + paymentServiceId + ", serviceName=" + serviceName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(paymentServiceId, serviceName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentService other = (PaymentService) obj;
		return Objects.equals(paymentServiceId, other.paymentServiceId)
				&& Objects.equals(serviceName, other.serviceName);
	}

}
