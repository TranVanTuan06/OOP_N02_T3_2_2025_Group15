package com.cafe.menu;

public abstract class Drink {
    protected String name;
    protected double price;

    public Drink(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract void prepare();
    public double getPrice() { return price; }

    public void display() {
        System.out.println(name + ": " + price + " VND");
    }
}

