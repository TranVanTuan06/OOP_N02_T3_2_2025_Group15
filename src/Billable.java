import java.util.List;
import java.util.ArrayList;
interface Billable {
    void calculateTotal();
    void printReceipt();
}
class Drink {
    private String name;
    private int price;

    public Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public int getPrice() { return price; }
    public String getName() { return name; }
}
class Order implements Billable {
    private List<Drink> items = new ArrayList<>();

    public void addDrink(Drink d) {
        items.add(d);
    }
    @Override
    public void calculateTotal() {
        int total = 0;
        for (Drink d : items) {
            total += d.getPrice();
        }
        System.out.println("💰 Tổng tiền đơn hàng: " + total + " VND");
    }
    @Override
    public void printReceipt() {
        System.out.println("🧾 Hoá đơn:");
        for (Drink d : items) {
            System.out.println("- " + d.getName() + ": " + d.getPrice() + " VND");
        }
        calculateTotal();
        System.out.println("✅ Hoá đơn đã được lưu.");
    }
}