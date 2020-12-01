package com.example.customer.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.customer.entity.Category;
import com.example.customer.entity.Product;
import com.example.customer.entity.ProductResponseByCategory;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	//h. Get all product by origin
	List<Product> findByOrigin(String origin);
	
	//h. Get all product by origin
	//@Query("SELECT p FROM Product p WHERE p.origin LIKE %?1%")
	@Query("SELECT p FROM Product p WHERE CONCAT(p.id, ' ', p.name, ' ', p.origin, ' ', p.price,' ') LIKE %?1%" )
	public List<Product> search(String keyword);
	
	//d. 
	List<Product> findAllById(Integer id);
	
	//b. check product by name
    Boolean existsByName(String name);
	Product findByName(String name);
	
	
		

}
	
	

