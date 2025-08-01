package com.cafe.menu;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuManagerTest {

    @Test
    public void testAddDrinkAndGetMenu() {
        MenuManager manager = new MenuManager();
        Drink drink = new Drink("Cà phê sữa", 25000);
        manager.addDrink(drink);

        List<Drink> menu = manager.getMenu();
        assertEquals(1, menu.size());
        assertEquals("Cà phê sữa", menu.get(0).getName());
        assertEquals(25000, menu.get(0).getPrice());
    }
}
