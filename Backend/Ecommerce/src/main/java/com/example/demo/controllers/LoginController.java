package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.AuthenticationResponse;
import com.example.demo.models.interfaces.DTOUser;
import com.example.demo.services.LoginServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
	
	@Autowired
	private LoginServices loginServices;
	
	@PostMapping("/login")
    public ResponseEntity<ObjectNode> login(@RequestBody DTOUser dtoUser, HttpServletRequest request){

        ObjectMapper objectMapper = new ObjectMapper();
        AuthenticationResponse authenticationResponse = this.loginServices.login(dtoUser.getEmail(), dtoUser.getChain());

        ObjectNode jsonToken = objectMapper.valueToTree(authenticationResponse);

        return ResponseEntity.ok(jsonToken);
    }
}
