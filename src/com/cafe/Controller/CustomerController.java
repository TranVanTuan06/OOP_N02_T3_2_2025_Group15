package com.cafe.Controller;

import com.cafe.Customer.Customer;
import com.cafe.Customer.CustomerManager;

import java.util.List;

public class CustomerController {
    private CustomerManager customerManager;

    public CustomerController() {
        customerManager = new CustomerManager();
    }

    public void addCustomer(String id, String name, String phone) {
        Customer customer = new Customer(id, name, phone);
        customerManager.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerManager.getCustomerList();
    }

    public Customer findCustomerById(String id) {
        return customerManager.findById(id);
    }

    public boolean deleteCustomer(String id) {
        return customerManager.deleteCustomer(id);
    }

    public void updateCustomer(String id, String newName, String newPhone) {
        Customer customer = customerManager.findById(id);
        if (customer != null) {
            customer.setName(newName);
            customer.setPhoneNumber(newPhone);
        }
    }
}
