package com.cafe.Customer;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager {

    ArrayList<Customer> getCustomerList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public ArrayList<Customer> addCustomer(Customer c) {
        getCustomerList.add(c);
        return getCustomerList;
    }

    public boolean xoaKhachHang(String customerID) {
        for (int i = 0; i < getCustomerList.size(); i++) {
            if (getCustomerList.get(i).getCustomerID().equals(customerID)) {
                getCustomerList.remove(i);
                return true;
            }
        }
        System.out.println("Không tìm thấy khách hàng với mã: " + customerID);
        return false;
   }

    public void getCustomerList() {
        if (getCustomerList.isEmpty()) {
            System.out.println("Không có khách hàng nào trong danh sách.");
            return;
        }
        for (Customer c : getCustomerList) {
            System.out.println("Mã KH: " + c.getCustomerID());
            System.out.println("Tên: " + c.getName());
            System.out.println("SĐT: " + c.getPhoneNumber());
            System.out.println("--------------------");
        }
    }

    public ArrayList<Customer> getCustomerListData() {
    return getCustomerList;
}


    public ArrayList<Customer> chinhSuaKhachHang(String customerID) {
        for (Customer c : getCustomerList) {
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
        return getCustomerList;
    }
    public Customer findById(String customerID) {
    for (Customer c : getCustomerList) {
        if (c.getCustomerID().equals(customerID)) {
            return c;
        }
    }
    return null;
}

    public void dongScanner() {
        scanner.close();
    }
}

