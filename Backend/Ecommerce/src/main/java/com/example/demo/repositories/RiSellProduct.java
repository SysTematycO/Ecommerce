package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.SellProducts;

@Repository
public interface RiSellProduct extends JpaRepository<SellProducts, Integer>{
	
	@Query(value = "SELECT id_product, price, COUNT(*) AS sale_count " +
            "FROM sellproducts " +
            "GROUP BY id_product, price " +
            "ORDER BY sale_count DESC " +
            "LIMIT 5", nativeQuery = true)
	List<Object[]> getAllProductsPopular();
	
	
	@Query(value = "SELECT buyer_email, COUNT(*) AS sale_count\r\n"
			+ "FROM sellproducts\r\n"
			+ "GROUP BY buyer_email\r\n"
			+ "ORDER BY sale_count DESC\r\n"
			+ "LIMIT 5", nativeQuery = true)
	List<Object[]> getAllClientPopular();
}
