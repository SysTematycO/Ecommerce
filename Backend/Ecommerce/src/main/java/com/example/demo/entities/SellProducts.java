package com.example.demo.entities;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SELLPRODUCTS")
public class SellProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SELL")
    private int idSell;
    
    @Column(name = "ID_PRODUCT")
    private int idProduct;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "SALE_DATE")
    private Date saleDate;

    @Column(name = "PUBLICATION_DATE")
    private Date publicationDate;

    @Column(name = "BUYER_EMAIL")
    private String buyerEmail;

    @Column(name = "SELLER_EMAIL")
    private String sellerEmail;
    
    @Column(name = "TOTAL_VALUE")
    private double valueTotal;
    
    public SellProducts() {}

    public int getIdSell() {
		return idSell;
	}

	public void setIdSell(int idSell) {
		this.idSell = idSell;
	}

	public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

	public double getValueTotal() {
		return valueTotal;
	}

	public void setValueTotal(double valueTotal) {
		this.valueTotal = valueTotal;
	}
}
