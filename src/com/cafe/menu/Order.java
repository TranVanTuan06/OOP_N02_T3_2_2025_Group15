package com.cafe.menu;

import java.util.ArrayList;

public class Order implements Payable {
    private ArrayList<Drink> items = new ArrayList<>();

    public void addDrink(Drink d) {
        items.add(d);
    }

    public double getPaymentAmount() {
        double total = 0;
        for (Drink d : items) {
            total += d.getPrice();
        }
        return total;
    }
}
