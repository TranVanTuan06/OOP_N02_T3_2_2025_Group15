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
        for (int i = 0; i < danhSachKhachHang.size(); i++) {
            if (danhSachKhachHang.get(i).customerID.equals(customerID)) {
                danhSachKhachHang.remove(i);
                break;
            }
        }
        return danhSachKhachHang;
    }

    public void hienThiKhachHang() {
        for (Customer c : danhSachKhachHang) {
            System.out.println("Mã KH: " + c.customerID);
            System.out.println("Tên: " + c.name);
            System.out.println("SĐT: " + c.phoneNumber);
            System.out.println("--------------------");
        }
    }

    public ArrayList<Customer> chinhSuaKhachHang(String customerID) {
        for (Customer c : danhSachKhachHang) {
            if (c.customerID.equals(customerID)) {
                System.out.print("Nhập tên mới: ");
                String newName = scanner.nextLine();

                System.out.print("Nhập số điện thoại mới: ");
                String newPhone = scanner.nextLine();

                c.name = newName;
                c.phoneNumber = newPhone;
                break;
            }
        }
        return danhSachKhachHang;
    }

    public void dongScanner() {
        scanner.close();
    }
}

