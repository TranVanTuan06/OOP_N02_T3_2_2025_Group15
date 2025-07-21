package com.cafe.menu;

public class OrderManager {
    private Order order = new Order();  // has-a (composition)

    public void addItem(com.cafe.menu.Drink d) {
        order.addDrink(d);
    }

    public void printTotal() {
        System.out.println("Tổng hóa đơn: " + order.getPaymentAmount() + " VND");
    }
}
