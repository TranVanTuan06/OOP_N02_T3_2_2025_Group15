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
        try {
            boolean found = false;
            for (int i = 0; i < danhSachKhachHang.size(); i++) {
                if (danhSachKhachHang.get(i).customerID.equalsIgnoreCase(customerID)) {
                    danhSachKhachHang.remove(i);
                    System.out.println("Đã xoá khách hàng có mã: " + customerID);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy khách hàng với mã: " + customerID);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xoá khách hàng: " + e.getMessage());
        }
        return danhSachKhachHang;
    }

    public void hienThiKhachHang() {
        if (danhSachKhachHang.isEmpty()) {
            System.out.println("Không có khách hàng nào trong danh sách.");
            return;
        }

        for (Customer c : danhSachKhachHang) {
            System.out.println("Mã KH: " + c.customerID);
            System.out.println("Tên: " + c.name);
            System.out.println("SĐT: " + c.phoneNumber);
            System.out.println("--------------------");
        }
    }

    public ArrayList<Customer> chinhSuaKhachHang(String customerID) {
        try {
            boolean found = false;
            for (Customer c : danhSachKhachHang) {
                if (c.customerID.equalsIgnoreCase(customerID)) {
                    System.out.print("Nhập tên mới: ");
                    String newName = scanner.nextLine();

                    System.out.print("Nhập số điện thoại mới: ");
                    String newPhone = scanner.nextLine();

                    c.name = newName;
                    c.phoneNumber = newPhone;
                    System.out.println("Đã cập nhật thông tin cho khách hàng " + customerID);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy khách hàng với mã: " + customerID);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi chỉnh sửa khách hàng: " + e.getMessage());
        }
        return danhSachKhachHang;
    }

    public void dongScanner() {
        scanner.close();
    }
}

