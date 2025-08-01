package com.cafe.bill;

interface Billable {
    void calculateTotal();
    void printReceipt();
}
public class Drink {
    private String name;
    private int price;

    public Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public int getPrice() { return price; }
    public String getName() { return name; }
}