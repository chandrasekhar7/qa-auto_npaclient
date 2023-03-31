package com.netpay.apimodel;

public class User {
	private Long id;

	private String name;

	private String email;

	public Long getId() {

	return id;

	}

	public User setId(Long id) {

	this.id = id;

	return this;

	}

	public String getName() {

	return name;

	}

	public User setName(String name) {

	this.name = name;

	return this;

	}

	public String getEmail() {

	return email;

	}

	public User setEmail(String email) {

	this.email = email;

	return this;

	}

	@Override

	public String toString() {

	final StringBuilder sb = new StringBuilder("{") ;

	sb.append("id=").append(id);

	sb.append(", name='").append(name).append('\'');

	sb.append(", email='").append(email).append('\'');

	sb.append('}');

	return sb.toString();

	}




}
