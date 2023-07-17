package com.fred.store;

import com.fred.store.customer.Customer;
import com.fred.store.customer.CustomerService;
import com.fred.store.customer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        when(repository.save(customer)).thenReturn(customer);

        Customer result = customerService.saveCustomer(customer);

        assertEquals(customer, result);
        verify(repository, times(1)).save(customer);
    }

    @Test
    public void testSaveCustomers() {
        List<Customer> customers = new ArrayList<>();
        when(repository.saveAll(customers)).thenReturn(customers);

        List<Customer> result = customerService.saveCustomers(customers);

        assertEquals(customers, result);
        verify(repository, times(1)).saveAll(customers);
    }

    @Test
    public void testUpdateCustomer() {
        int customerId = 1;
        Customer existingCustomer = new Customer();
        existingCustomer.setId(customerId);
        existingCustomer.setName("John");
        existingCustomer.setAddress("123 Main St");
        existingCustomer.setActive(true);

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setName("Jane");
        updatedCustomer.setAddress("456 Elm St");
        updatedCustomer.setActive(false);

        when(repository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(repository.save(existingCustomer)).thenReturn(existingCustomer);

        Customer result = customerService.updateCustomer(updatedCustomer);

        assertEquals(existingCustomer, result);
        assertEquals(updatedCustomer.getName(), existingCustomer.getName());
        assertEquals(updatedCustomer.getAddress(), existingCustomer.getAddress());
        assertEquals(updatedCustomer.isActive(), existingCustomer.isActive());
        verify(repository, times(1)).findById(customerId);
        verify(repository, times(1)).save(existingCustomer);
    }

    @Test
    public void testGetCustomerById() {
        int customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(repository.findById(customerId)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(customerId);

        assertEquals(customer, result);
        verify(repository, times(1)).findById(customerId);
    }

    @Test
    public void testGetCustomers() {
        List<Customer> customers = new ArrayList<>();
        when(repository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getCustomers();

        assertEquals(customers, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerByStatus() {
        boolean activeStatus = true;
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setActive(true);
        customers.add(customer1);
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setActive(false);
        customers.add(customer2);

        when(repository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getCustomerByStatus(activeStatus);

        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(customer -> customer.isActive() == activeStatus));
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testDeleteCustomer() {
        int customerId = 1;

        String expectedResult = "{\r\n"
                + "	\"message\": \"Customer " + customerId + " deleted\"\r\n"
                + "}";

        customerService.deleteCustomer(customerId);

        verify(repository, times(1)).deleteById(customerId);
    }
}
