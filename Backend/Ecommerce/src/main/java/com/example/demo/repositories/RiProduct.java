package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Products;

@Repository
public interface RiProduct extends JpaRepository<Products, Integer>{

	Optional<Products> findByIdProduct(int idProduct);
	
	@Query(value = "SELECT * FROM PRODUCTS WHERE ID_PRODUCT IN (:idProducts)", nativeQuery = true)
	List<Products> getAllProductsId(List<Integer> idProducts);
}
