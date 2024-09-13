package com.cardstore.dao;

import java.util.List;

/**
 * @author Sera Jeong 12211242 Created Date: 17/08/2024
 */

public interface GenericDAO<E> {

	public E create(E t);

	public E update(E t);

	public E get(Object id);

	public void delete(Object id);

	public List<E> listAll();

	public long count();
}
