package com.cafe.menu;

public class Tea extends Drink {
    public Tea(String name, double price) {
        super(name, price);
    }

    public void prepare() {
        System.out.println("Đang pha trà: " + name);
    }
}
