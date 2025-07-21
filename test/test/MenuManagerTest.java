package test;
import com.cafe.menu.*;
public class MenuManagerTest {
    public static void main(String[] args) {
        MenuManager menu = new MenuManager();
        menu.addDrink(new Coffee("Cafe Sữa", 20000));
        menu.addDrink(new Tea("Trà Chanh", 15000));
        menu.displayMenu();
        menu.serveAllDrinks();
    }
}