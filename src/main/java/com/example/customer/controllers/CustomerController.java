package com.example.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.customer.entity.Customer;

import com.example.customer.repository.CustomerRepository;
import com.example.customer.services.CustomerService;



@Controller
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	CustomerService customerService;
	
	
	
	@GetMapping("/customer")
	public String viewCustomerHome(Model model) {
		
		List<Customer> listCustomers = customerRepo.findAll();
		model.addAttribute("listCustomers", listCustomers);
		
		
		return "customer_home";
		//return "index";
	
	}
	
	
	//a. create customer
	@PostMapping("/createCustomer")
	public String createCustomer(@ModelAttribute("customer")Customer customer) {
		 
		customerRepo.save(customer);

		return "redirect:/customer";
	}
	
	
	@GetMapping("/showNewCustomerForm")                   
	public String showNewCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);           
		
		return "new_customer";
	}
	
	
	//a. show form for update customer
	@GetMapping("/showUpdateCustomerForm/{id}")
	public String showUpdateCustomerForm(@PathVariable( value = "id")Integer id, Model model) {
		Customer customer = customerRepo.findById(id).get();
		
		model.addAttribute("customer", customer);
		
		return "update_customer";
	}
	
	
	//a. delete customer
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable("id")Integer id) {
		customerRepo.deleteById(id);
		return "redirect:/customer";
	}
	
	
	
}
