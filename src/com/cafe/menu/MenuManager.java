package com.cafe.menu;

import java.util.ArrayList;

public class MenuManager {
    private ArrayList<Drink> drinks = new ArrayList<>();

    public void addDrink(Drink d) {
        drinks.add(d);
    }

    public void displayMenu() {
        for (Drink d : drinks) {
            d.display();
        }
    }

    public void serveAllDrinks() {
        for (Drink d : drinks) {
            d.prepare();  // Polymorphism!
        }
    }
}

