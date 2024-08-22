package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.PaymentService;

/**
 * @author Sera Jeong 12211242
 * Created Date: 18/08/2024
 */

public class PaymentServiceDAO extends JpaDAO<PaymentService> implements GenericDAO<PaymentService> {
	public PaymentServiceDAO() {
	}

	public PaymentService create(PaymentService paymentService) {
		return super.create(paymentService);
	}

	@Override
	public PaymentService update(PaymentService paymentService) {
		return super.update(paymentService);
	}

	@Override
	public PaymentService get(Object paymentServiceId) {
		return super.find(PaymentService.class, paymentServiceId);
	}

	@Override
	public void delete(Object paymentServiceId) {
		super.delete(PaymentService.class, paymentServiceId);
	}

	@Override
	public List<PaymentService> listAll() {
		return super.findWithNamedQuery("PaymentService.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("PaymentService.countAll");
	}

}
