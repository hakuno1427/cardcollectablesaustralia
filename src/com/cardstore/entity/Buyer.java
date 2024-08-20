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
@Table(name="buyer")
@NamedQueries({
	@NamedQuery(name = "Buyer.findAll", query = "SELECT b from Buyer b ORDER BY b.email"),	
	@NamedQuery(name = "Buyer.countAll", query = "SELECT Count(*) FROM Buyer b"),
	@NamedQuery(name = "Buyer.findByEmail", query = "SELECT b FROM Buyer b WHERE b.email = :email")
})

public class Buyer extends Person implements Serializable{
	private static final long serialVersionUID = 1L;

	public Buyer() {

	}

	public Buyer(String firstName, String lastName, Integer phone, String email, String password) {
		super(firstName, lastName, phone, email, password);
	}
}
