package com.cardstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cardstore.entity.User;

/**
 * @author Sera Jeong 12211242 Created Date: 17/08/2024
 */

public class UserDAO extends JpaDAO<User> implements GenericDAO<User> {

	public UserDAO() {
	}

	public User create(User user) {
		return super.create(user);
	}

	@Override
	public User update(User user) {
		return super.update(user);
	}

	@Override
	public User get(Object email) {
		return super.find(User.class, email);
	}

	@Override
	public void delete(Object email) {
		super.delete(User.class, email);
	}

	@Override
	public List<User> listAll() {
		return super.findWithNamedQuery("User.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("User.countAll");
	}

	public User findByEmail(String email) {

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);

		List<User> result = super.findWithNamedQuery("User.findByEmail", parameters);

		if (!result.isEmpty()) {
			return result.get(0);
		}

		return null;
	}

	public User checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("pass", password);

		List<User> result = super.findWithNamedQuery("User.checkLogin", parameters);

		if (!result.isEmpty()) {
			return result.get(0);
		}

		return null;
	}

}
