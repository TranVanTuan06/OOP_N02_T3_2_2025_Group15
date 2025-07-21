package com.cafe.menu;

import java.util.ArrayList;

public class StaffManager {
    private ArrayList<Employee> staffList = new ArrayList<>();

    public void addStaff(String name) {
        staffList.add(new Employee(name));
    }

    public void displayStaff() {
        for (Employee e : staffList) {
            e.showInfo();
        }
    }
}
