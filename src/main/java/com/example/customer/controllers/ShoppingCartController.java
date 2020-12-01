package com.example.customer.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.customer.entity.Customer;
import com.example.customer.entity.Product;
import com.example.customer.entity.ShoppingCart;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.ProductRepository;
import com.example.customer.repository.ShoppingCartRepository;
import com.example.customer.services.ShoppingCartService;


@Controller   
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartRepository shoppingCartRepo;
	
	@Autowired
	ShoppingCartService shoppingCartService; 
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	private List<Product> listaSoProdukti = new ArrayList<Product>();
	
	// Get all shopping carts
		@GetMapping("/shoppingCart")
		public String viewShoppingCartHome(Model model) {
			
			List<Product> listProducts = productRepo.findAll();
			List<ShoppingCart> listShoppingCarts = shoppingCartRepo.findAll();
			model.addAttribute("listShoppingCarts", listShoppingCarts);
			model.addAttribute("listProducts", listProducts);
			return "cart_home";
		
		
		}
		
		@PostMapping(value="/addProductsToSC")
		public String cartsAddProducts(@ModelAttribute("cart") ShoppingCart cart, @RequestParam (value = "productId", required = false) Integer productId, @RequestParam (value = "customerId", required = false) Integer customerId) {
			
			
			Customer customer = customerRepo.findById(customerId).get();
			cart.setCustomer(customer);
			
			Product p = productRepo.findById(productId).get();
			//ShoppingCart cart = shoppingCartRepo.findById(id).get();
			
			//if (cart != null) {
			    List<Product> a = new ArrayList<Product>();
			    a.add(p);
			    cart.setProducts(a);
			    
			//}
				
			
			//model.addAttribute("cart", shoppingCartRepo.findAll());
			//model.addAttribute("products", productRepo.findAll());
			
			return "redirect:/shoppingCart";
			
		}
		
		
	/*	//Add to shopping cart
	   @GetMapping(value="/addToShoppingCart/{productId}")
        public String addToShoppingCart(@PathVariable ("productId")Integer productId) {
			Product p = productRepo.findById(productId).get();
			listaSoProdukti.add(p);
			
			return "redirect:/product";
			
        	
        }
		
	//create order or shopping cart, and Add product to Shopping Cart
		 @PostMapping(value="/createShoppingCart")
		    public String saveOrder(@RequestParam Integer customerId, @ModelAttribute("cart") ShoppingCart cart){
                
			    Date date = new Date(System.currentTimeMillis());
			 
		       Customer customer = customerRepo.findById(customerId).get();
		        
		       // ShoppingCart cart = new ShoppingCart();
		        //Customer customer = customerRepo.findById(customerId).get();
		       
			    
			    
			    cart.setCustomer(customerRepo.findById(customerId).get());

		        
		        cart.setProducts(listaSoProdukti);
		       
		       
		        shoppingCartRepo.save(cart);

		        listaSoProdukti.clear();
		        
		        return "redirect:/shoppingCart";
		        
		        
		       
		    }*/
		
		
		// Create Shopping Cart
   /*   // d. Add product to shopping cart- addProductToSC(product)	
		@PostMapping(value="/createShoppingCart/{personId}")
		public ShoppingCart createShoppingCart(@RequestParam("personId")Integer personId,@RequestBody List<Integer> productId) {
        
	   ShoppingCart  shoppingCart = new ShoppingCart();
		
		Date date = new Date(System.currentTimeMillis());
		
		Customer customer = customerRepo.findById(personId).get();
		
		
		List<Product> products = productRepo.findAllById(productId);
		
		shoppingCart.setCreatedOn(date);
		shoppingCart.setCustomer(customer);
		shoppingCart.setProducts(products);
		
		return shoppingCartRepo.save(shoppingCart);
		
	
	   } */
		 
		 
		 //create sc
		/* @PostMapping(value="/createShoppingCart")
		    public String saveOrder( @RequestParam Integer customerId,@ModelAttribute("cart") ShoppingCart cart,@ModelAttribute("product") Product product){
             
			    Date date = new Date(System.currentTimeMillis());
			 
		        Customer customer = customerRepo.findById(customerId).get();
		        
		        List<Product> listaSoProdukti = new ArrayList<Product>();
		        Product p = productRepo.findByName(product).get();
				listaSoProdukti.add(p);
		        
		       // List<Product> products = productRepo.findAll();
		       // listaSoProdukti.add((Product) products);
		        
		       // ShoppingCart cart = new ShoppingCart();
		        //Customer customer = customerRepo.findById(customerId).get();
		        cart.setCustomer(customerRepo.findById(customerId).get());

		        
		        cart.setProducts(listaSoProdukti);
		       
		       
		        shoppingCartRepo.save(cart);

		        //listaSoProdukti.clear();
		        
		        return "redirect:/shoppingCart";
		        
		        
		       
		    }*/
	
	 
	
	
	@GetMapping("/showNewShopingCartForm")                   
	public String showNewShoppingCart(Model model) {
		List<Product> listProducts = productRepo.findAll();
		model.addAttribute("listProducts", listProducts);
		
		ShoppingCart cart = new ShoppingCart();
		model.addAttribute("cart", cart);        
		
		return "new_cart";
	}
	
	
	
	// e. Remove Product from Shopping Cart- removeProductFromSC
/*	@DeleteMapping(value="/removeProductFromSC/{cartId}")
	@Transactional
	public ShoppingCart removeProductFromSC(@RequestParam("cartId")Integer cartId, Integer productId ) {
		return shoppingCartService.removeProductFromSC(cartId,productId);
	
	}*/
	
	//e-1. Remove all products from shopping cart
/*	@DeleteMapping(value="/removeAllProductsFromSC/{cartId}")
	@Transactional
	public ShoppingCart removeAllProductsFromSC(@RequestParam("cartId")Integer cartId) {
		return shoppingCartService.removeAllProductsFromSC(cartId);
		
	}*/
	
	//e-2.Remove shopping cart
	@DeleteMapping(value="/removeSC/{cartId}")
	@Transactional
	public void removeSC(@RequestParam("cartId")Integer cartId) {
		
		shoppingCartRepo.deleteById(cartId);
		
	}
	
	
	//f. get shopping cart by id
	@GetMapping("/getShoppingCartById/{id}")
	public ShoppingCart getShoppingCartById(@RequestParam("id")Integer id) {

	return shoppingCartRepo.findById(id).get();
	}
	
	
	//f-1. get all shopping carts
	@GetMapping("/getAllShoppingCarts")
	public List<ShoppingCart> getAllShoppingCarts() {

		return shoppingCartRepo.findAll();
	}
	
	
	//f-2. get all shopping carts by Customer
	@GetMapping("/getShoppingCartByCustomerId/{customerId}")
	public List<ShoppingCart> getShoppingCartByCustomerId(@RequestParam("customerId")Integer customerId) {

	return  shoppingCartRepo.findAllSCByCustomer(customerId);
	}
		
		
	
	

	
}

