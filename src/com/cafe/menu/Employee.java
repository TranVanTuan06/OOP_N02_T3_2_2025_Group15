package com.cafe.menu;

public class Employee extends Person {
    private String role;
    private double salary;

    public Employee(String name, String role, double salary) {
        super(name);
        this.role = role;
        this.salary = salary;
    }

    public String getRole() { return role; }
    public double getSalary() { return salary; }

    public void setRole(String role) { this.role = role; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public void display() {
        System.out.println("" + name + " | Chức vụ: " + role + " | Lương: " + salary + " VND");
    }
}
