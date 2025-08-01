package com.cafe.main;

import com.cafe.controller.MenuController;

public class App {
    public static void main(String[] args) {
        try {
            MenuController.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
