package com.example.demo.models.interfaces;

import java.util.Date;

import com.example.demo.models.Product;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Product.class)
public interface DTOProduct {
	
	String getEmail();
	String getName();
	String getDescription();
	double getPrice();
	Date getPublication_date();
	int getQuantity();
}
