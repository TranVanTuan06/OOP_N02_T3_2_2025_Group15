package com.cafe.menu;

public class Employee extends Person {
    public Employee(String name) {
        super(name);
    }

    public void showInfo() {
        System.out.println("Nhân viên: " + name);
    }
}
