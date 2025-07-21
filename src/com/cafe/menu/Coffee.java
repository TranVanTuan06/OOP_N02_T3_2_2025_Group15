package com.cafe.menu;

public class Coffee extends Drink {
    public Coffee(String name, double price) {
        super(name, price);
    }

    public void prepare() {
        System.out.println("Đang pha cà phê: " + name);
    }
}

