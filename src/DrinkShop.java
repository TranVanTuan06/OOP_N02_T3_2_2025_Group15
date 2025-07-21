import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrinkShop {
    private List<Customer> customers = new ArrayList<>();
    private List<Table> tables = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addCustomer(Customer c) {
        customers.add(c);
        System.out.println("✔️ Đã thêm khách hàng: " + c.getName());
    }

    public void readCustomers() {
        System.out.println("📋 Danh sách khách hàng:");
        for (Customer c : customers) {
            System.out.println("- " + c.getCustomerID() + ": " + c.getName() + ", SĐT: " + c.getPhoneNumber());
        }
    }

    public void editCustomer(String id) {
        for (Customer c : customers) {
            if (c.getCustomerID().equals(id)) {
                System.out.print("✏️ Nhập tên mới cho khách: ");
                String newName = scanner.nextLine();
                c.setName(newName);
                System.out.println("✅ Đã cập nhật tên khách hàng.");
                return;
            }
        }
        System.out.println("⚠️ Không tìm thấy khách hàng với ID: " + id);
    }

    public void deleteCustomer(String id) {
        customers.removeIf(c -> c.getCustomerID().equals(id));
        System.out.println("🗑️ Đã xoá khách hàng có ID: " + id);
    }

    // Quản lý bàn
    public void addTable(Table t) {
        tables.add(t);
        System.out.println("✔️ Đã thêm bàn: " + t.getTableID());
    }

    public void readTables() {
        System.out.println("📋 Danh sách bàn:");
        for (Table t : tables) {
            System.out.println("- " + t.getTableID() + " (Sức chứa: " + t.getCapacity() + ", Có khách: " + t.hasCustomer() + ")");
        }
    }

    public void editTable(String id) {
        for (Table t : tables) {
            if (t.getTableID().equals(id)) {
                System.out.print("✏️ Nhập sức chứa mới: ");
                int newCap = Integer.parseInt(scanner.nextLine());
                t.setCapacity(newCap);
                System.out.println("✅ Đã cập nhật sức chứa.");
                return;
            }
        }
        System.out.println("⚠️ Không tìm thấy bàn với ID: " + id);
    }

    public void deleteTable(String id) {
        tables.removeIf(t -> t.getTableID().equals(id));
        System.out.println("🗑️ Đã xoá bàn có ID: " + id);
    }

    public void closeScanner() {
        if (scanner != null) scanner.close();
    }
}