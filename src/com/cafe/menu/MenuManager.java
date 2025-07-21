package com.cafe.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private List<Drink> drinks;

    public MenuManager() {
        drinks = new ArrayList<>();
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
        System.out.println("Đã thêm: " + drink.getName());
    }

    public void displayMenu() {
        System.out.println("DANH SÁCH MENU:");
        for (Drink drink : drinks) {
            drink.display();
        }
    }

    public void serveAllDrinks() {
        for (Drink drink : drinks) {
            drink.prepare();
            drink.incrementOrder();
        }
    }

    public Drink findDrinkByName(String name) {
        for (Drink drink : drinks) {
            if (drink.getName().equalsIgnoreCase(name)) {
                return drink;
            }
        }
        return null;
    }

    public List<Drink> findByType(DrinkType type) {
        List<Drink> result = new ArrayList<>();
        for (Drink drink : drinks) {
            if (drink.getType() == type) {
                result.add(drink);
            }
        }
        return result;
    }

    public boolean updateDrink(String name, double newPrice) {
        Drink drink = findDrinkByName(name);
        if (drink != null) {
            drink.updatePrice(newPrice);
            System.out.println("Đã cập nhật giá cho: " + name);
            return true;
        }
        return false;
    }

    public boolean removeDrinkByName(String name) {
        Drink drink = findDrinkByName(name);
        if (drink != null) {
            drinks.remove(drink);
            System.out.println("Đã xóa: " + name);
            return true;
        }
        return false;
    }

    public void removeDrinksByType(DrinkType type) {
        drinks.removeIf(drink -> drink.getType() == type);
        System.out.println("Đã xóa tất cả món thuộc loại: " + type);
    }

    public Drink getMostOrderedDrink() {
        if (drinks.isEmpty()) return null;
        Drink best = drinks.get(0);
        for (Drink drink : drinks) {
            if (drink.getOrderCount() > best.getOrderCount()) {
                best = drink;
            }
        }
        return best;
    }

    public void exportMenuToFile(String filename) {
        System.out.println("Xuất menu ra file '" + filename + "' (chức năng chưa triển khai)");
    }
}


