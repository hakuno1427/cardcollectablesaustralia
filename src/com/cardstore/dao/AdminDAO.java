package com.cardstore.dao;

import java.util.List;

import com.cardstore.entity.Admin;

/**
 * @author Sera Jeong 12211242
 * Created Date: 18/08/2024
 */

public class AdminDAO extends JpaDAO<Admin> implements GenericDAO<Admin> {
	public AdminDAO() {
	}
	
	public Admin create(Admin admin) {
		return super.create(admin);
	}
	
	@Override
	public Admin update(Admin admin) {
		return super.update(admin);
	}
	
	@Override
	public Admin get(Object adminId) {
		return super.find(Admin.class, adminId);
	}

	@Override
	public void delete(Object adminId) {
		super.delete(Admin.class, adminId);	
	}

	@Override
	public List<Admin> listAll() {
		return super.findWithNamedQuery("Admin.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Admin.countAll");
	}

}
