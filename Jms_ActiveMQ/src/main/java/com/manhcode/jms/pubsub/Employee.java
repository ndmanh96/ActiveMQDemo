package com.manhcode.jms.pubsub;

import java.io.Serializable;

public class Employee implements Serializable {
	private int id;
	private String name;
	private String email;
	private String destination;
	private String phone;
	
	public Employee() {}

	public Employee(int id, String name, String email, String destination, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.destination = destination;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", destination=" + destination
				+ ", phone=" + phone + "]";
	}
	
	
}
