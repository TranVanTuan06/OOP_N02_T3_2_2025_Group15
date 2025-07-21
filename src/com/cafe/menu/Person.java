package com.cafe.menu;

public class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public void showInfo() {
        System.out.println("Tên: " + name);
    }
}

