public class testBillable {
    public static void main(String[] args) {
        Order order = new Order();
        order.addDrink(new Drink("Cà phê đen", 20000));
        order.addDrink(new Drink("Nước cam", 25000));
        order.addDrink(new Drink("Trà đào", 30000));
        order.printReceipt();
    }
}
