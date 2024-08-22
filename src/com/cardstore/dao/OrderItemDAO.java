package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.OrderItem;

/**
 * @author Sera Jeong 12211242
 * Created Date: 18/08/2024
 */

public class OrderItemDAO extends JpaDAO<OrderItem> implements GenericDAO<OrderItem> {
	public OrderItemDAO() {
	}
	
	public OrderItem create(OrderItem orderItem) {
		return super.create(orderItem);
	}
	
	@Override
	public OrderItem update(OrderItem orderItem) {
		return super.update(orderItem);
	}
	
	@Override
	public OrderItem get(Object orderItemId) {
		return super.find(OrderItem.class, orderItemId);
	}

	@Override
	public void delete(Object orderItemId) {
		super.delete(OrderItem.class, orderItemId);	
	}

	@Override
	public List<OrderItem> listAll() {
		return super.findWithNamedQuery("OrderItem.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("OrderItem.countAll");
	}

}
