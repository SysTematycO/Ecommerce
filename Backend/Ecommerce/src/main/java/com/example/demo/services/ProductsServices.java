package com.example.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Products;
import com.example.demo.models.interfaces.DTOBuyProduct;
import com.example.demo.models.interfaces.DTOProduct;
import com.example.demo.repositories.RiProduct;

@Service
public class ProductsServices {

	@Autowired
	private RiProduct riProduct;
	
	@Autowired
	private AuditServices auditServices;

	public ProductsServices() {}

	public List<Products> getAllProducts() {
		return this.riProduct.findAll();
	}

	public boolean addProduct(DTOProduct dtoProduct) {

		Products products = new Products();
		
	    products.setEmail(dtoProduct.getEmail());
	    products.setName(dtoProduct.getName());
	    products.setDescription(dtoProduct.getDescription());
	    products.setPrice(dtoProduct.getPrice());
	    products.setQuantity(dtoProduct.getQuantity());
	    products.setPublication_date(new Date());
		
		try {
			this.riProduct.save(products);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean buyProduct(DTOBuyProduct dtoBuyProduct) {

		return this.riProduct.findByIdProduct(dtoBuyProduct.getIdProduct()).map(existingProduct -> {
			int checkQuantity = existingProduct.getQuantity() - dtoBuyProduct.getQuantityToBuy();
			
			if(checkQuantity > 0) {
				existingProduct.setQuantity(checkQuantity);
				
				try {
	                this.riProduct.save(existingProduct);
	                this.auditServices.AddSalesAuditProducts(dtoBuyProduct);
	                return true;
	            } catch (Exception e) {
	                return false;
	            }
			} else if(checkQuantity == 0) {
				this.deleteProduct(existingProduct.getIdProduct());
				return true;
			} else {
				return false;
			}

        }).orElse(false);
	}
	
	 public boolean deleteProduct(int idProduct) {
	    	
	        return this.riProduct.findByIdProduct(idProduct).map(product -> {
	            try {
	                this.riProduct.delete(product);
	                return true;
	            } catch (Exception e) {
	                return false;
	            }
	        }).orElse(false); 
	 }
}
