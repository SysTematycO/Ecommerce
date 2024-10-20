package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Products;
import com.example.demo.entities.SellProducts;
import com.example.demo.models.interfaces.DTOBuyProduct;
import com.example.demo.repositories.RiProduct;
import com.example.demo.repositories.RiSellProduct;

@Service
public class AuditServices {

	@Autowired
	private RiSellProduct riSellProduct;
	
	@Autowired
	private RiProduct riProduct;

	public AuditServices() {}

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
	
	public List<Products> getAllProductsPopular(){
		
		List<Object[]> productPopularity = this.riSellProduct.getAllProductsPopular();
		List<Integer> idProductsPopular = new ArrayList<>();
		
		for (Object[] result : productPopularity) {
	        int idProduct = (int) result[0];
	        idProductsPopular.add(idProduct);
	    }
		
		return this.riProduct.getAllProductsId(idProductsPopular);
	}
	
	public List<String> getAllClientPopular(){
		
		List<Object[]> clientPopularity = this.riSellProduct.getAllClientPopular();
		List<String> clients = new ArrayList<>();
		
		for (Object[] result : clientPopularity) {
	        String buyerEmail = (String) result[0];
	        clients.add(buyerEmail);
	    }
		
		return clients;
	}
}
