package com.fred.store.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fred.store.customer.Customer;
import com.fred.store.dto.LinkRequest;

@RequestMapping("/api/v1")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/addCustomerAndOrder")
	public Customer addCustomerAndOrder(@RequestBody Customer customer) {
		return orderService.addCustomerAndOrder(customer);
	}
	
	@PostMapping("/addOrder")
	public Order addOrder(@RequestBody Order order) {
		return orderService.addOrder(order);
	}
	
	@GetMapping("/orders")
	public List<Order> getOrders(){
		return orderService.getOrders();
	}
	
	@PostMapping("/linkOrder")
	public Customer linkOrderToCustomer(@RequestBody LinkRequest linkRequest) {
		return orderService.linkOrderToCustomer(linkRequest);
	}
}
