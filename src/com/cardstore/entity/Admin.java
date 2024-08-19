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
@Table(name="admin")
@NamedQueries({
	@NamedQuery(name = "Admin.findAll", query = "SELECT a from Admin a ORDER BY a.adminId"),	
	@NamedQuery(name = "Admin.countAll", query = "SELECT Count(*) FROM Admin a")	
})

public class Admin implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer adminId;
	private String adminEmail;

	public Admin() {

	}

	public Admin(Integer adminId, String adminEmail) {
		super();
		this.adminId = adminId;
		this.adminEmail = adminEmail;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adminId", unique = true, nullable = false)
	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@Column(name = "adminEmail", nullable = false, length = 320)
	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminEmail=" + adminEmail + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminEmail, adminId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(adminEmail, other.adminEmail) && Objects.equals(adminId, other.adminId);
	}

}
