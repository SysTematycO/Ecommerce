package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.AuditServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@RestController
@RequestMapping("/audit")
public class AuditController {
	
	@Autowired
	private AuditServices auditServices;
	
	@GetMapping("/getClientPopular")
	public ResponseEntity<ArrayNode> getClientPopular(@RequestHeader("Authorization") String token) {

		ObjectMapper objectMapper = new ObjectMapper();

		List<String> clientPopular = this.auditServices.getAllClientPopular();

		ArrayNode clientsArray = objectMapper.createArrayNode();

        for (String client : clientPopular) {
            clientsArray.add(client);
        }
		return ResponseEntity.ok(clientsArray);
	}
}