package com.cafe.menu;

public class Coffee extends Drink {

    public Coffee(String name, double price, DrinkType type) {
        super(name, price, type);
    }

    public Coffee(String name, double price) {
        super(name, price, DrinkType.CAFFEINE);
    }

    @Override
    public void prepare() {
        System.out.println("Pha cà phê: " + getName());
    }
}


