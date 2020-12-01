package com.example.customer.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;




@Service
public class  CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	

	
}