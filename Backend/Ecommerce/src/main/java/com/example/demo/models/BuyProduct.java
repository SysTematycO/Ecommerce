package com.example.demo.models;

import java.util.Date;

import com.example.demo.models.interfaces.DTOBuyProduct;

public class BuyProduct implements DTOBuyProduct {

	private int idProduct;
    private String emailBuyer;
    private String emailSeller;
    private String name;
    private double price;
    private Date publicationDate;
    private int quantityToBuy;

    public BuyProduct() {}

    @Override
    public int getIdProduct() {
		return idProduct;
	}

	@Override
    public String getEmailBuyer() {
        return emailBuyer;
    }
	
	@Override
    public String getEmailSeller() {
        return emailSeller;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override
    public int getQuantityToBuy() {
        return quantityToBuy;
    }

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

    public void setEmailBuyer(String emailBuyer) {
		this.emailBuyer = emailBuyer;
	}

	public void setEmailSeller(String emailSeller) {
		this.emailSeller = emailSeller;
	}

	public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setQuantityToBuy(int quantityToBuy) {
        this.quantityToBuy = quantityToBuy;
    }
}
