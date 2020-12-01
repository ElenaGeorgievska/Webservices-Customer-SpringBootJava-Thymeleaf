package com.example.customer.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.customer.entity.Category;
import com.example.customer.entity.Customer;
import com.example.customer.entity.Manufacturer;
import com.example.customer.entity.Product;

import com.example.customer.entity.ProductResponseByCategory;
import com.example.customer.entity.ProductResponseByDiscount;
import com.example.customer.entity.ShoppingCart;
import com.example.customer.repository.CategoryRepository;
import com.example.customer.repository.ManufacturerRepository;
import com.example.customer.repository.ProductRepository;
import com.example.customer.services.ProductService;



@Controller
public class ProductController {
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	ManufacturerRepository manufacturerRepo;
	
	@Autowired
	ProductService productService;
	
	
	//c. Get all products- getAllProducts() and // h.Get all products by origin
	@GetMapping("/product")
	public String viewProductHome(Model model,@Param("keyword") String keyword) {
				
		findPaginated(1, "name", "asc", model);
		
		List<Product> listProducts1 = productService.listAll(keyword);
		model.addAttribute("listProducts", listProducts1);
		model.addAttribute("keyword", keyword);
		
		ShoppingCart cart = new ShoppingCart();
		model.addAttribute("cart", cart);
		
		return "product_home";
		
	   	
	}
	
	
	//b.create product
	@PostMapping("/createProduct")
	public String create(@ModelAttribute("product")Product product) {
		productService.create(product);
        return "redirect:/product";
	}
	
	
	@GetMapping("/showNewProductForm")                   
	public String showNewProduct(Model model) {
		
		Product product = new Product();
		model.addAttribute("product", product);           
		
		return "new_product";
	}
	
	
	//b. update product
	@GetMapping("/showUpdateProductForm/{id}")
	public String showUpdateProductForm(@PathVariable("id")Integer id, Model model) {
		Product product = productRepo.findById(id).get();
		
		model.addAttribute("product", product);
		
		return "update_product";
	} 
	
	
	//b. delete product
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id")Integer id) {
		productRepo.deleteById(id);
		return "redirect:/product";
	}
	
	
	    
	//paging and sorting
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") Integer pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		Integer pageSize = 5;
		
		Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Product> listProducts = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listProducts", listProducts);
		
		
		return "product_home";
	}
	

	
}
	
	
	

