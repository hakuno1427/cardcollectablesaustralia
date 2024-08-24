package com.cardstore.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
@NamedQueries({ @NamedQuery(name = "Role.findAll", query = "SELECT r from Role r ORDER BY r.id"),
		@NamedQuery(name = "Role.countAll", query = "SELECT Count(*) FROM Role r"),
		@NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name"), })
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String BUYER_ROLE = "buyer";
	public static final String SELLER_ROLE = "seller";
	public static final String ADMIN_ROLE = "admin";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "role")
	private Set<User> users = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> permissions = new HashSet<>();

	// Getters, setters
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}
}