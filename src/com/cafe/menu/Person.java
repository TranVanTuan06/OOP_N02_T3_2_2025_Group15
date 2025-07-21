package com.cafe.menu;

public abstract class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public abstract void display();
}

