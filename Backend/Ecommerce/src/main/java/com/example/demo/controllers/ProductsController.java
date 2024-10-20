package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Products;
import com.example.demo.models.interfaces.DTOBuyProduct;
import com.example.demo.models.interfaces.DTOProduct;
import com.example.demo.services.ProductsServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/product")
public class ProductsController {

	@Autowired
	private ProductsServices productsServices;

	@GetMapping("/getAllProducts")
	public ResponseEntity<ArrayNode> getAllProducts(@RequestHeader("Authorization") String token) {

		ObjectMapper objectMapper = new ObjectMapper();

		List<Products> products = this.productsServices.getAllProducts();

		ArrayNode usersArray = objectMapper.createArrayNode();

	    products.forEach(product -> usersArray.add(objectMapper.valueToTree(product)));

		return ResponseEntity.ok(usersArray);
	}
	
	@GetMapping("/getAllProductsPopular")
	public ResponseEntity<ArrayNode> getAllProductsPopular(@RequestHeader("Authorization") String token) {

		ObjectMapper objectMapper = new ObjectMapper();

		List<Products> products = this.productsServices.getAllProductsPopular();

		ArrayNode usersArray = objectMapper.createArrayNode();

	    products.forEach(product -> usersArray.add(objectMapper.valueToTree(product)));

		return ResponseEntity.ok(usersArray);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<ObjectNode> addProduct(@RequestHeader("Authorization") String token,
			@RequestBody DTOProduct dtoProduct) {

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode responseJson = objectMapper.createObjectNode();

		boolean addUser = this.productsServices.addProduct(dtoProduct);

		if (addUser) {
			responseJson.put("status", "success");
			responseJson.put("message", "Producto agregado exitosamente");
			return ResponseEntity.ok(responseJson);
		} else {
			responseJson.put("status", "error");
			responseJson.put("message", "Error al agregar el producto");
			return ResponseEntity.ok(responseJson);
		}

	}

	@PutMapping("/buyProduct")
	public ResponseEntity<ObjectNode> buyProduct(@RequestHeader("Authorization") String token,
			@RequestBody DTOBuyProduct dtoBuyProduct) {

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode responseJson = objectMapper.createObjectNode();

		boolean sellProduct = this.productsServices.buyProduct(dtoBuyProduct);
		
		if(sellProduct) {
			responseJson.put("status", "success");
			responseJson.put("message", "Producto vendido correctamente");
			return ResponseEntity.ok(responseJson);
		} else {
			responseJson.put("status", "error");
			responseJson.put("message", "No hay disponibilidad suficiente");
			return ResponseEntity.ok(responseJson);
		}

	}
	
	@DeleteMapping("/deleteProduct/{idProduct}")
    public ResponseEntity<ObjectNode> deleteUser(@RequestHeader("Authorization") String token, @PathVariable int idProduct) {
    	
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode responseJson = objectMapper.createObjectNode();

        boolean deleteUser = this.productsServices.deleteProduct(idProduct);

        if (deleteUser) {
            responseJson.put("status", "success");
            responseJson.put("message", "Producto eliminado exitosamente");
            return ResponseEntity.ok(responseJson);
        } else {
            responseJson.put("status", "error");
            responseJson.put("message", "No se pudo eliminar el producto o no existe");
            return ResponseEntity.ok(responseJson);
        }
    }

}