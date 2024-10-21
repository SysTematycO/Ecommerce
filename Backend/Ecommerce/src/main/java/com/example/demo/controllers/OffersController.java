package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Offers;
import com.example.demo.services.OffersServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@RestController
@RequestMapping("/offers")
public class OffersController {

	@Autowired
	private OffersServices offersServices;
	
	@GetMapping("/getOffers")
	public ResponseEntity<ArrayNode> getOffers(@RequestHeader("Authorization") String token) {

		ObjectMapper objectMapper = new ObjectMapper();

		List<Offers> offersList = this.offersServices.getOffers();

		ArrayNode offersArray = objectMapper.createArrayNode();

		for (Offers offer : offersList) {
			offersArray.add(objectMapper.valueToTree(offer));
        }
		
		return ResponseEntity.ok(offersArray);
	}
}
