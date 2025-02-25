package com.cardstore.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u from User u ORDER BY u.firstName"),
		@NamedQuery(name = "User.countAll", query = "SELECT Count(*) FROM User u"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery(name = "User.checkLogin", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :pass AND u.verified = 1"),
		@NamedQuery(name ="User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
	    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role.name = :roleName"),
	    @NamedQuery(name = "User.findByVerificationToken", query = "SELECT u FROM User u WHERE u.verificationToken = :verificationToken")})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final byte YES_VALUE = 1;
	public static final byte NO_VALUE = 0;

	private Integer userId;
	private String firstName;
	private String lastName;
	private Integer phone;
	private String email;
	private String password;
	private Role role;
	private String description;
	private String verificationToken;
	private byte verified = NO_VALUE;
	private byte enabled = YES_VALUE;
	

	@OneToMany(mappedBy = "listing")
	private Set<Listing> listings = new HashSet<>();

	public User() {
	}

	public User(String firstName, String lastName, Integer phone, String email, String password, String description) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "firstName", nullable = false, length = 50)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", nullable = false, length = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "phone", nullable = false)
	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	@Column(name = "email", nullable = false, length = 320)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 80)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Set<Listing> getListings() {
		return listings;
	}
	
	public void setListings(Set<Listing> listings) {
		this.listings = listings;
	}
	

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	@Column(name = "enabled", nullable = false)
	public byte getEnabled() {
		return this.enabled;
	}
	
	 @Column(name = "description", nullable = true, length = 2000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	 @Column(name = "verificationToken", nullable = true, length = 2000)
	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

	public byte getVerified() {
		return verified;
	}

	@Column(name = "verified", nullable = true)
	public void setVerified(byte verified) {
		this.verified = verified;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, password, phone);
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + ", role=" + role + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone);
	}
}
