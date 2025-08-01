package com.cafe.Customer;
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
            if (danhSachKhachHang.get(i).getCustomerID().equals(customerID)) {
                danhSachKhachHang.remove(i);
                break;
            }
        }

    boolean found = false;
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

    public ArrayList<Customer> chinhSuaKhachHang(String customerID) {
        for (Customer c : danhSachKhachHang) {
            if (c.getCustomerID().equals(customerID)) {
                System.out.print("Nhập tên mới: ");
                String newName = scanner.nextLine();

                System.out.print("Nhập số điện thoại mới: ");
                String newPhone = scanner.nextLine();

                c.setName(newName);
                c.setPhoneNumber(newPhone);
                break;
            }
        }
        return danhSachKhachHang;
    }
    public void dongScanner() {
        scanner.close();
    }
}

