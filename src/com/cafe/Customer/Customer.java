package com.cafe.Customer;
public class Customer {
    private String customerID;
    private String name;
    private String phoneNumber;

    public Customer(String customerID, String name, String phoneNumber) {
        this.customerID = customerID;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setName(String newName) { this.name = newName; }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}