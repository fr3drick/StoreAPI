package com.fred.store.dto;

import lombok.ToString;

@ToString
public class LinkRequest {

	private int cust_id;
	private int order_id;
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public LinkRequest(int cust_id, int order_id) {
		super();
		this.cust_id = cust_id;
		this.order_id = order_id;
	}
	
	
	
}
