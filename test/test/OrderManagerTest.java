package test;
import com.cafe.menu.*;
public class OrderManagerTest {
    public static void main(String[] args) {
        OrderManager order = new OrderManager();
        order.addItem(new Coffee("Espresso", 25000));
        order.addItem(new Tea("Trà Đào", 18000));
        order.printTotal();
    }
}
