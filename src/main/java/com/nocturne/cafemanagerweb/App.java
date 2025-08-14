package com.nocturne.cafemanagerweb;

import com.nocturne.cafemanagerweb.view.MainUI;

public class App {
    public static void main(String[] args) {
        // ví dụ tạo UI (Swing) nếu MainUI là JFrame/JPanel...
        MainUI ui = new MainUI();
        // ui.setVisible(true); // nếu là JFrame
        System.out.println("App started with MainUI");
    }
}

