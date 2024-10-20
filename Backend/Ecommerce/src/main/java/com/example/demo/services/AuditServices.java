package com.example.demo.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.SellProducts;
import com.example.demo.models.interfaces.DTOBuyProduct;
import com.example.demo.repositories.RiSellProduct;

@Service
public class AuditServices {

	@Autowired
	private RiSellProduct riSellProduct;

	public AuditServices() {
	}

	public boolean AddSalesAuditProducts(DTOBuyProduct dtoBuyProduct) {

		SellProducts sellProducts = new SellProducts();

		sellProducts.setIdProduct(dtoBuyProduct.getIdProduct());
		sellProducts.setName(dtoBuyProduct.getName());
		sellProducts.setPrice(dtoBuyProduct.getPrice());
		sellProducts.setQuantity(dtoBuyProduct.getQuantityToBuy());
		sellProducts.setPublicationDate(dtoBuyProduct.getPublicationDate()); 
		sellProducts.setBuyerEmail(dtoBuyProduct.getEmailBuyer());
		sellProducts.setSellerEmail(dtoBuyProduct.getEmailSeller());
		sellProducts.setSaleDate(new Date());
		sellProducts.setValueTotal(dtoBuyProduct.getPrice() * dtoBuyProduct.getQuantityToBuy());

		try {
			this.riSellProduct.save(sellProducts);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
