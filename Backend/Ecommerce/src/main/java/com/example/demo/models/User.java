package com.example.demo.models;

import com.example.demo.models.interfaces.DTOUser;

public class User implements DTOUser{

	private String email;
	private String chain;
	private String role;
	
	public User() {}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getChain() {
		return chain;
	}

	public void setChain(String chain) {
		this.chain = chain;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}