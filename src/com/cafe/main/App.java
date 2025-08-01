package com.cafe.main;

import com.cafe.menu.*;

public class App {
    public static void main(String[] args) {
        MenuManager manager = new MenuManager();

        manager.addDrink(new Drink("Espresso", 30000));
        manager.addDrink(new Drink("Latte", 40000));
        manager.addDrink(new Drink("Trà đào", 35000));

        System.out.println("Menu hiện tại:");
        for (Drink d : manager.getMenu()) {
            System.out.println(d);
        }

        manager.removeDrink("Latte");

        System.out.println("\nMenu sau khi xoá:");
        for (Drink d : manager.getMenu()) {
            System.out.println(d);
        }
    }
}
