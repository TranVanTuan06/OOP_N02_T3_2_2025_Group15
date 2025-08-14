package com.nocturne.cafemanagerweb.view.panel;

import javax.swing.*;
import java.awt.*;

public class TinhTienPanel extends JPanel {
    public TinhTienPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Trang Tính Tiền", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);
    }
}

