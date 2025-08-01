import com.cafe.Controller.CustomerController;
import com.cafe.Customer.Customer;

public class testCustomerController {
    public static void main(String[] args) {
        CustomerController controller = new CustomerController();
        controller.addCustomer("KH001", "Nguyễn Văn A", "0912345678");

        for (Customer c : controller.getCustomerList()) {
            System.out.println("Mã KH: " + c.getCustomerID());
            System.out.println("Tên: " + c.getName());
            System.out.println("SĐT: " + c.getPhoneNumber());
            System.out.println("--------------------");
        }
    }
}
