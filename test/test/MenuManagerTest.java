package test;

import com.cafe.menu.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuManagerTest {
    @Test
    public void testAddAndRemoveDrink() {
        MenuManager manager = new MenuManager();
        Drink coffee = new Drink("Cà phê", 25000);
        manager.addDrink(coffee);

        assertEquals(1, manager.getMenu().size());
        assertTrue(manager.removeDrink("Cà phê"));
        assertEquals(0, manager.getMenu().size());
    }
}
