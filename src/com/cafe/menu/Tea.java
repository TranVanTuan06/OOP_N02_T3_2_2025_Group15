package com.cafe.menu;

public class Tea extends Drink {

    public Tea(String name, double price, DrinkType type) {
        super(name, price, type);
    }

    public Tea(String name, double price) {
        super(name, price, DrinkType.NON_CAFFEINE);
    }

    @Override
    public void prepare() {
        System.out.println("Pha trà: " + getName());
    }
}

