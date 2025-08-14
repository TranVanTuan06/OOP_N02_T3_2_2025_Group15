package com.nocturne.cafemanagerweb;

import com.nocturne.cafemanagerweb.view.MainUI;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI ui = new MainUI();  // giả sử MainUI extends JFrame
            ui.setLocationRelativeTo(null); // canh giữa màn hình
            ui.setVisible(true);
        });
    }
}

