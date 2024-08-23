package com.cardstore.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
	private Set<Role> roles = new HashSet<>();

	public Permission() {
	}

	public Permission(String name) {
		this.name = name;
	}

	public Object getName() {
		return name;
	}
}
