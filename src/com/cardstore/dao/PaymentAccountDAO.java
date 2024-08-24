package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.PaymentAccount;

/**
 * @author Sera Jeong 12211242 Created Date: 18/08/2024
 */

public class PaymentAccountDAO extends JpaDAO<PaymentAccount> implements GenericDAO<PaymentAccount> {

	public PaymentAccountDAO() {
	}

	public PaymentAccount create(PaymentAccount paymentAccount) {
		return super.create(paymentAccount);
	}

	@Override
	public PaymentAccount update(PaymentAccount paymentAccount) {
		return super.update(paymentAccount);
	}

	@Override
	public PaymentAccount get(Object paymentAccountId) {
		return super.find(PaymentAccount.class, paymentAccountId);
	}

	@Override
	public void delete(Object paymentAccountId) {
		super.delete(PaymentAccount.class, paymentAccountId);
	}

	@Override
	public List<PaymentAccount> listAll() {
		return super.findWithNamedQuery("PaymentAccount.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("PaymentAccount.countAll");
	}

}
