import com.cafe.Customer.Customer;
import com.cafe.Customer.CustomerManager;

public class TestCustomerApp {
    public static void test() {
    try {
        CustomerManager manager = new CustomerManager();

        Customer c1 = new Customer("KH001", "Nguyễn Văn A", "0905123456");
        Customer c2 = new Customer("KH002", "Trần Thị B", "0912345678");

        manager.addCustomer(c1);
        manager.addCustomer(c2);

        System.out.println("=== Danh sách khách hàng ban đầu ===");
        manager.getCustomerList();

        System.out.println("=== Sau khi xoá KH001 ===");
        manager.xoaKhachHang("KH001");
        manager.getCustomerList();

        manager.dongScanner();
    } catch (Exception e) {
        System.out.println("Lỗi chương trình: " + e.getMessage());
    }
}
}
