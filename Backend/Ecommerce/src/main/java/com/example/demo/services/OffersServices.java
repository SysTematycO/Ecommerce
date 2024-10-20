package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Offers;
import com.example.demo.repositories.RiOffers;

@Service
public class OffersServices {

	@Autowired
    private RiOffers riOffers;
	
	public OffersServices() {}
	
	
	public List<Offers> getOffers() {
		return this.riOffers.findAll();
	}
}
