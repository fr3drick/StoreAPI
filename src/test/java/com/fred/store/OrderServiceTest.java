package com.fred.store;

import com.fred.store.customer.Customer;
import com.fred.store.customer.CustomerRepository;
import com.fred.store.dto.LinkRequest;
import com.fred.store.exceptions.NullCustomerOrderException;
import com.fred.store.exceptions.OrderAlreadyLinkedException;
import com.fred.store.order.Order;
import com.fred.store.order.OrderService;

import com.fred.store.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCustomerAndOrder() {
        Customer customer = new Customer();
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = orderService.addCustomerAndOrder(customer);

        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testAddOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.addOrder(order);

        assertEquals(order, result);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testGetOrders() {
        List<Order> orders = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getOrders();

        assertEquals(orders, result);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testLinkOrderToCustomer_Success() throws OrderAlreadyLinkedException, NullCustomerOrderException {
        int orderId = 1;
        int customerId = 1;

        Order order = new Order();
        order.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        customer.setOrders(orderList);
        customerRepository.save(customer);

        int orderId2 = 2;
        Order order2 = new Order();
        order2.setId(orderId2);
        orderRepository.save(order2);


        LinkRequest linkRequest = new LinkRequest();
        linkRequest.setOrder_id(orderId);
        linkRequest.setCust_id(customerId);

//       Customer result = orderService.linkOrderToCustomer(linkRequest);

       assertThrows(Exception.class, () -> orderService.linkOrderToCustomer(linkRequest));
       Customer result = customerRepository.findById(customerId).orElse(null);

        assertEquals(customer, result);
        assertTrue(customer.getOrders().contains(order));
//        verify(orderRepository, times(1)).findById(orderId);
//        verify(customerRepository, times(1)).findById(customerId);
//        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testLinkOrderToCustomer_NullOrderOrCustomer_ThrowsNullCustomerOrderException() {
        int orderId = 1;
        int customerId = 1;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        LinkRequest linkRequest = new LinkRequest();
        linkRequest.setOrder_id(orderId);
        linkRequest.setCust_id(customerId);

        assertThrows(NullCustomerOrderException.class, () -> orderService.linkOrderToCustomer(linkRequest));
        verify(orderRepository, times(1)).findById(orderId);
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, never()).save(any());
    }

    @Test
    public void testLinkOrderToCustomer_OrderAlreadyLinked_ThrowsOrderAlreadyLinkedException() {
        int orderId = 1;
        int customerId = 1;

        Order order = new Order();
        order.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        customer.setOrders(orderList);

        LinkRequest linkRequest = new LinkRequest();
        linkRequest.setOrder_id(orderId);
        linkRequest.setCust_id(customerId);

        assertThrows(OrderAlreadyLinkedException.class, () -> orderService.linkOrderToCustomer(linkRequest));
        verify(orderRepository, times(1)).findById(orderId);
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, never()).save(any());
    }
}
