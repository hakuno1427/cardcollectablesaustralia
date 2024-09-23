package com.cardstore.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cardstore.entity.Card;
import com.cardstore.entity.Role;
import com.cardstore.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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
	public User get(Object userId) {
		return super.find(User.class, userId);
	}

	@Override
	public void delete(Object userId) {
		super.delete(User.class, userId);
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
		List<User> result = super.findWithNamedQuery("User.findByEmail", "email", email);

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

	public List<User> listPaged(int start, int pageSize) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
    public List<User> findSellers() {
        String sellerRoleName = Role.SELLER_ROLE;
        return super.findWithNamedQuery("User.findByRole", "roleName", sellerRoleName);
    }
    

    public List<User> findBuyers() {
        String buyerRoleName = Role.BUYER_ROLE;
        return super.findWithNamedQuery("User.findByRole", "roleName", buyerRoleName);
    }

    public List<User> findAdmins() {
        String adminRoleName = Role.ADMIN_ROLE;
        return super.findWithNamedQuery("User.findByRole", "roleName", adminRoleName);
    }

    public List<User> findByRole(String roleName) {
        if (roleName.equals("ALL")) {
            return listAll(); 
        } else if (roleName.equals(Role.SELLER_ROLE)) {
            return findSellers();
        } else if (roleName.equals(Role.BUYER_ROLE)) {
            return findBuyers();
        } else if (roleName.equals(Role.ADMIN_ROLE)) {
            return findAdmins();
        } else {
            System.out.println("Warning: Unknown role: " + roleName);
            return Collections.emptyList();

    public List<User> findByRole(String roleName) {
        if (roleName.equals("ALL")) {
            return listAll(); 
        } else {
            return super.findWithNamedQuery("User.findByRole", "roleName", roleName);

        }
    }
}
