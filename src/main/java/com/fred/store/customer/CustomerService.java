package com.fred.store.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}
	
	public List<Customer> saveCustomers(List<Customer> customers){
		return repository.saveAll(customers);
	}
	
	public Customer updateCustomer(Customer customer) {
		Customer existingCustomer = repository.findById(customer.getId()).orElse(null);
		existingCustomer.setAddress(customer.getAddress());
		existingCustomer.setName(customer.getName());
		existingCustomer.setActive(customer.isActive());
		return repository.save(existingCustomer);
	}
	
	public Customer getCustomerById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Customer> getCustomers(){
		return repository.findAll();
	}
	
	public List<Customer> getCustomerByStatus(boolean active){
		List<Customer> ls = repository.findAll().stream().filter(s -> s.isActive() == active)
				.collect(Collectors.toList());
		return ls;
	
	}
	
	public String deleteCustomer(int id) {
		repository.deleteById(id);
		return "{\r\n"
				+ "	\"message\": \"Customer "+id+" deleted\"\r\n"
				+ "}";
	}
	
	
}
