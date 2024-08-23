package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Role;

public class RoleDAO extends JpaDAO<Role> implements GenericDAO<Role> {

	@Override
	public Role get(Object id) {
		return super.find(Role.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(Role.class, id);
	}

	@Override
	public List<Role> listAll() {
		return super.findWithNamedQuery("Role.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Role.countAll");
	}

	public Role findByName(String name) {
		List<Role> result = super.findWithNamedQuery("Role.findByName", "name", name);

		if (!result.isEmpty()) {
			return result.get(0);
		}

		return null;
	}
}
