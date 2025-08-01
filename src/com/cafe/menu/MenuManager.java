package com.cafe.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private List<Drink> menu = new ArrayList<>();

    public void addDrink(Drink drink) {
        menu.add(drink);
    }

    public boolean removeDrink(String name) {
        return menu.removeIf(d -> d.getName().equalsIgnoreCase(name));
    }

    public List<Drink> getMenu() {
        return menu;
    }
}
