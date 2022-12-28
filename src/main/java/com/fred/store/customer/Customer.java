package com.fred.store.customer;

import java.util.List;

import com.fred.store.order.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store_customer")
public class Customer {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String address;
	private boolean active;
	
	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cust_id", referencedColumnName = "id")
	private List<Order> orders;
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public Customer(int id, String name, String address, boolean active) {
		
		this.id = id;
		this.name = name;
		this.address = address;
		this.active = active;
	}
	
	public Customer() {}
	public Customer(int id, String name, String address, boolean active, List<Order> orders) {
		
		this.id = id;
		this.name = name;
		this.address = address;
		this.active = active;
		this.orders = orders;
	}
	
	
	
	
	
}
