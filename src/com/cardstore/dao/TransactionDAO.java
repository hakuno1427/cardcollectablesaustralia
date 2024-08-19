package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Transaction;

public class TransactionDAO extends JpaDAO<Transaction> implements GenericDAO<Transaction> {
	public TransactionDAO() {
	}

	public Transaction create(Transaction transaction) {
		return super.create(transaction);
	}

	@Override
	public Transaction update(Transaction transaction) {
		return super.update(transaction);
	}

	@Override
	public Transaction get(Object transactionId) {
		return super.find(Transaction.class, transactionId);
	}

	@Override
	public void delete(Object transactionId) {
		super.delete(Transaction.class, transactionId);
	}

	@Override
	public List<Transaction> listAll() {
		return super.findWithNamedQuery("Transaction.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Transaction.countAll");
	}

}
