package com.example.demo.models;

import java.util.List;

public class AuthenticationResponse {
	
	private String email;
	
	private String token;
	
	private List<String> role;
	
	private String status;

	public AuthenticationResponse(){}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}