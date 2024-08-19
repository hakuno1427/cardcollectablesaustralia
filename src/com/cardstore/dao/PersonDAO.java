package com.cardstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cardstore.entity.Person;

public class PersonDAO extends JpaDAO<Person> implements GenericDAO<Person> {

	public PersonDAO() {
	}

	public Person create(Person person) {
		return super.create(person);
	}

	@Override
	public Person update(Person person) {
		return super.update(person);
	}

	@Override
	public Person get(Object email) {
		return super.find(Person.class, email);
	}

	@Override
	public void delete(Object email) {
		super.delete(Person.class, email);
	}

	@Override
	public List<Person> listAll() {
		return super.findWithNamedQuery("Person.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Person.countAll");
	}
	
	public Person findByEmail(String email) {
		List<Person> result = super.findWithNamedQuery("Person.findByEmail", "email", email);
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}

	public Person checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", email);
		parameters.put("pass", password);
		
		List<Person> result = super.findWithNamedQuery("Person.checkLogin", parameters);
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		
		return null;
	}

}
