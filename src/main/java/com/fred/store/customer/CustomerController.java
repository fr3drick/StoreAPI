package com.fred.store.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	CustomerService service;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return service.getCustomers();
	}
	
	@GetMapping("/customers/status")
	public List<Customer> getCustomersByStatus(@RequestParam boolean active){
		return service.getCustomerByStatus(active);
	}
	
	@GetMapping("/customer")
	public Customer getCustomerById(@RequestParam int id) {
		return service.getCustomerById(id);
	}
	
	@PostMapping("/customer/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}
	
	@PostMapping("/customer/add-more")
	public List<Customer> addCustomers(@RequestBody List<Customer> customers) {
		return service.saveCustomers(customers);
	}
	
	@PutMapping("/customer/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}
	
	@DeleteMapping("/customer/delete")
	public String deleteCustomer(@RequestParam int id) {
		return service.deleteCustomer(id);
	}


}
