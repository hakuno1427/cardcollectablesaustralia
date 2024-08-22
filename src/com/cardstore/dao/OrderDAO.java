package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Order;

/**
 * @author Sera Jeong 12211242
 * Created Date: 17/08/2024
 */

public class OrderDAO extends JpaDAO<Order> implements GenericDAO<Order> {
	public OrderDAO() {
	}

	public Order create(Order order) {
		return super.create(order);
	}

	@Override
	public Order update(Order order) {
		return super.update(order);
	}

	@Override
	public Order get(Object orderId) {
		return super.find(Order.class, orderId);
	}

	@Override
	public void delete(Object orderId) {
		super.delete(Order.class, orderId);
	}

	@Override
	public List<Order> listAll() {
		return super.findWithNamedQuery("Order.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Order.countAll");
	}

}
