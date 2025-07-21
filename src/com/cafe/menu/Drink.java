package com.cafe.menu;

public abstract class Drink {
    private String name;
    private double price;
    private DrinkType type;
    private int orderCount;
    private double rating;

    public Drink(String name, double price, DrinkType type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.orderCount = 0;
        this.rating = 0;
    }

    public abstract void prepare();

    public String getName() { return name; }
    public double getPrice() { return price; }
    public DrinkType getType() { return type; }
    public int getOrderCount() { return orderCount; }

    public void display() {
        System.out.println(name + " (" + type + ") - " + price + " VND");
    }

    public void incrementOrder() {
        orderCount++;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}

