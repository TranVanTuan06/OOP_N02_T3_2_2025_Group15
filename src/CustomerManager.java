import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager {

    ArrayList<Customer> danhSachKhachHang = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public ArrayList<Customer> themKhachHang(Customer c) {
        danhSachKhachHang.add(c);
        return danhSachKhachHang;
    }

    public ArrayList<Customer> xoaKhachHang(String customerID) {
    boolean found = false;

    for (int i = 0; i < danhSachKhachHang.size(); i++) {
        Customer c = danhSachKhachHang.get(i);
        if (c.getCustomerID().equals(customerID)) {
            danhSachKhachHang.remove(i);
            System.out.println(" Đã xoá khách hàng có mã: " + customerID);
            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println(" Không tìm thấy khách hàng với mã: " + customerID);
    }

    return danhSachKhachHang;
}

    public void hienThiKhachHang() {
        if (danhSachKhachHang.isEmpty()) {
            System.out.println("Không có khách hàng nào trong danh sách.");
            return;
        }
        for (Customer c : danhSachKhachHang) {
            System.out.println("Mã KH: " + c.getCustomerID());
            System.out.println("Tên: " + c.getName());
            System.out.println("SĐT: " + c.getPhoneNumber());
            System.out.println("--------------------");
        }
    }


    public void dongScanner() {
        scanner.close();
    }
}

