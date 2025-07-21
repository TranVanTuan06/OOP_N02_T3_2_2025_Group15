package com.cafe.menu;

import java.util.ArrayList;
import java.util.List;

public class StaffManager {
    private List<Employee> employees;

    public StaffManager() {
        employees = new ArrayList<>();
    }

    public void addStaff(String name, String role, double salary) {
        Employee e = new Employee(name, role, salary);
        employees.add(e);
        System.out.println("Đã thêm nhân viên: " + name);
    }

    public boolean removeStaffByName(String name) {
        Employee toRemove = findByName(name);
        if (toRemove != null) {
            employees.remove(toRemove);
            System.out.println("Đã xóa nhân viên: " + name);
            return true;
        }
        return false;
    }

    public Employee findByName(String name) {
        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(name)) return e;
        }
        return null;
    }

    public void displayStaff() {
        System.out.println("DANH SÁCH NHÂN VIÊN:");
        for (Employee e : employees) {
            e.display();
        }
    }

    public boolean updateSalary(String name, double newSalary) {
        Employee e = findByName(name);
        if (e != null) {
            e.setSalary(newSalary);
            System.out.println("Cập nhật lương cho " + name + ": " + newSalary + " VND");
            return true;
        }
        return false;
    }

    public boolean updateRole(String name, String newRole) {
        Employee e = findByName(name);
        if (e != null) {
            e.setRole(newRole);
            System.out.println("Cập nhật chức vụ cho " + name + ": " + newRole);
            return true;
        }
        return false;
    }
}

