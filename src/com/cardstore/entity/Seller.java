package com.cardstore.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="seller")
@NamedQueries({
	@NamedQuery(name = "Seller.findAll", query = "SELECT s from Seller s ORDER BY s.email"),	
	@NamedQuery(name = "Seller.countAll", query = "SELECT Count(*) FROM Seller s"),
	@NamedQuery(name = "Seller.findByEmail", query = "SELECT s FROM Seller s WHERE s.email = :email")
})
public class Seller extends Person implements Serializable{
	private static final long serialVersionUID = 1L;

	public Seller() {

	}

	public Seller(String firstName, String lastName, Integer phone, String email, String password) {
		super(firstName, lastName, phone, email, password);
	}
}
