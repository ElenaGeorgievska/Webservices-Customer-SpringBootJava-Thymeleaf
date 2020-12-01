package com.example.customer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.customer.entity.Category;
import com.example.customer.entity.Manufacturer;
import com.example.customer.entity.Product;
import com.example.customer.entity.ProductResponseByCategory;
import com.example.customer.entity.ProductResponseByDiscount;
import com.example.customer.repository.CategoryRepository;
import com.example.customer.repository.ManufacturerRepository;
import com.example.customer.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	ManufacturerRepository manufacturerRepo;

	// h.Get all products by origin
	public List<Product> listAll(String keyword) {
		if (keyword != null) {
			return productRepo.search(keyword);
		}
		return productRepo.findAll();
	}

	// b.create product
	public Product create(Product product) {
		if (manufacturerRepo.existsByName(product.getManufacturer().getName())) {

			Manufacturer existingManufacturer = manufacturerRepo.findByName(product.getManufacturer().getName());

			product.setManufacturer(existingManufacturer);

		}

		if (categoryRepo.existsByproductCategory(product.getCategory().getProductCategory())) {

			Category existingCategory = categoryRepo.findByproductCategory(product.getCategory().getProductCategory());

			product.setCategory(existingCategory);
		}

		return productRepo.save(product);
	}


	
	// paging and sorting
	public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.productRepo.findAll(pageable);
	}


}
