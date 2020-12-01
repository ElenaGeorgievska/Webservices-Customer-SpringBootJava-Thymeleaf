package com.example.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.customer.entity.Customer;
import com.example.customer.entity.Product;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.ProductRepository;



@Controller
public class ViewController {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		
		
		List<Customer> listCustomers = customerRepo.findAll();
		model.addAttribute("listCustomers", listCustomers);
		
		
		return "index";
	}

}
