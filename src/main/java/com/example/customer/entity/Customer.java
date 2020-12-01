package com.example.customer.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Customer: (Id, firstName, lastName, Age, AddressId)

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private Integer age;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="customer_roles", joinColumns = @JoinColumn(name="customer_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Roles> roles= new HashSet<>();

	@ManyToOne (fetch=FetchType.EAGER, cascade = CascadeType.ALL )            
	@JoinColumn(referencedColumnName = "id")
	private Address address;

}
