package main.java;

import main.java.com.nocturne.cafemanagerweb.view.MainUI;

public class App {
    public static void main(String[] args) {
        // Khởi chạy giao diện chính
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }
}
