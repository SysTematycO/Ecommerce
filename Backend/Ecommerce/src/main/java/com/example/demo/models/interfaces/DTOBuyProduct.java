package com.example.demo.models.interfaces;

import java.util.Date;

import com.example.demo.models.BuyProduct;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = BuyProduct.class)
public interface DTOBuyProduct {

	int getIdProduct();
	String getEmailBuyer();
	String getEmailSeller();
	String getName();
	double getPrice();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	Date getPublicationDate();
	int getQuantityToBuy();
}
