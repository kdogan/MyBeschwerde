package de.features;

import java.sql.Date;

public class DbUser {
	private Long id;
	private String firstName;
	private String lastName;
	private String passoword;
	private String email;

	public DbUser() {
		//nop
	}
	public DbUser(Long id, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passoword = password;

	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long i) {
		this.id = i;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassoword() {
		return passoword;
	}
	public void setPassoword(String passoword) {
		this.passoword = passoword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
