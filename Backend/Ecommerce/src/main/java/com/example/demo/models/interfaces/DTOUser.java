package com.example.demo.models.interfaces;

import com.example.demo.models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = User.class)
public interface DTOUser {

	String getEmail();
	String getChain();
}