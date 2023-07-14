package com.fred.store.order;

import java.util.ArrayList;
import java.util.List;
import com.fred.store.exceptions.OrderAlreadyLinkedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fred.store.customer.Customer;
import com.fred.store.customer.CustomerRepository;
import com.fred.store.dto.LinkRequest;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer addCustomerAndOrder(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public List<Order> getOrders(){
		return orderRepository.findAll();
	}
	
	public Customer linkOrderToCustomer(LinkRequest linkRequest) throws OrderAlreadyLinkedException {
		Order order = orderRepository.findById(linkRequest.getOrder_id()).orElse(null);
		Customer customer = customerRepository.findById(linkRequest.getCust_id()).orElse(null);
		List<Order> orderList = customer.getOrders();
		if(orderList.contains(order))
		{
			throw  new OrderAlreadyLinkedException("Order already linked");
		};
		orderList.add(order);
		customer.setOrders(orderList);
		
		return customerRepository.save(customer);
	}
}

