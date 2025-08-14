package com.nocturne.cafemanagerweb.view.panel;

import com.nocturne.cafemanagerweb.model.ChiTietDon;
import com.nocturne.cafemanagerweb.model.DonHang;
import com.nocturne.cafemanagerweb.view.MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

public class NhapDon extends JPanel {

    private JTextField soLuongField;
    private JTextField giaField;
    private JTextField khachHangField;
    private JTextArea ghiChuArea;

    public NhapDon() {
        initComponents();
        setSize(500, 400);
    }

    private void initComponents() {
        // Panel nền gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color c1 = new Color(52, 152, 219);
                Color c2 = new Color(41, 128, 185);
                g2d.setPaint(new GradientPaint(0, 0, c1, getWidth(), getHeight(), c2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("NHẬP ĐƠN HÀNG");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Form
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(5, 2, 15, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel tenMonLabel = createLabel("Tên Món:");
        JComboBox<String> tenMonComboBox = new JComboBox<>(new String[]{
                "Cà phê sữa", "Cà phê đen", "Trà đào", "Trà sữa", "Sinh tố", "Nước cam"
        });
        tenMonComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel soLuongLabel = createLabel("Số Lượng:");
        soLuongField = createTextField();

        JLabel giaLabel = createLabel("Giá (VND):");
        giaField = createTextField();

        JLabel khachHangLabel = createLabel("Khách Hàng:");
        khachHangField = createTextField();

        JLabel ghiChuLabel = createLabel("Ghi Chú:");
        ghiChuArea = new JTextArea(3, 20);
        ghiChuArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ghiChuArea.setLineWrap(true);
        ghiChuArea.setWrapStyleWord(true);
        JScrollPane ghiChuScroll = new JScrollPane(ghiChuArea);
        ghiChuScroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        formPanel.add(tenMonLabel);
        formPanel.add(tenMonComboBox);
        formPanel.add(soLuongLabel);
        formPanel.add(soLuongField);
        formPanel.add(giaLabel);
        formPanel.add(giaField);
        formPanel.add(khachHangLabel);
        formPanel.add(khachHangField);
        formPanel.add(ghiChuLabel);
        formPanel.add(ghiChuScroll);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton nhapDonButton = new JButton("Nhập Đơn");
        styleButton(nhapDonButton);
        nhapDonButton.addActionListener((ActionEvent e) -> {
            String monDaChon = (String) tenMonComboBox.getSelectedItem();
            String slTxt = soLuongField.getText().trim();
            String giaTxt = giaField.getText().trim();
            String tenKhach = khachHangField.getText().trim();
            String ghiChu = ghiChuArea.getText().trim();

            if (monDaChon == null || monDaChon.isBlank()
                    || slTxt.isEmpty() || giaTxt.isEmpty() || tenKhach.isEmpty()) {
                JOptionPane.showMessageDialog(NhapDon.this, "Vui lòng nhập đủ thông tin");
                return;
            }

            Integer soLuong = parseSoLuong(slTxt);
            BigDecimal donGia = parseDonGia(giaTxt);
            if (soLuong == null || donGia == null) return;

            DonHang donHang = finDH(tenKhach);
            if (donHang != null) {
                ChiTietDon ctDH = donHang.getMon(monDaChon);
                if (ctDH == null) {
                    donHang.getChiTiet().add(new ChiTietDon(monDaChon, soLuong, donGia, ghiChu));
                } else {
                    ctDH.setSoLuong(ctDH.getSoLuong() + soLuong);
                    // Nếu muốn cập nhật đơn giá mới, bỏ comment:
                    // ctDH.setDonGia(donGia);
                }
                donHang.setGhiChu(ghiChu);
                MainUI.ghiVaoXML();
            } else {
                donHang = new DonHang(tenKhach);
                donHang.getChiTiet().add(new ChiTietDon(monDaChon, soLuong, donGia, ghiChu));
                donHang.setGhiChu(ghiChu);
                MainUI.addDonHang(donHang);
            }

            resetForm();
            JOptionPane.showMessageDialog(
                    NhapDon.this,
                    "Bạn đã chọn món: " + monDaChon + "\nĐơn hàng đã được nhập thành công!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        buttonPanel.add(nhapDonButton);

        // Assemble
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void resetForm() {
        soLuongField.setText("");
        giaField.setText("");
        ghiChuArea.setText("");
    }

    /** Tìm đơn theo tên khách trong danh sách tĩnh của MainUI */
    private DonHang finDH(String tenKH) {
        if (tenKH == null) return null;
        for (DonHang dh : MainUI.listDonHang) {
            if (tenKH.trim().equals(dh.getTenKhach())) {
                return dh;
            }
        }
        return null;
    }

    private Integer parseSoLuong(String sl) {
        try {
            int val = Integer.parseInt(sl);
            if (val <= 0 || val > 100_000) {
                JOptionPane.showMessageDialog(this, "Vui lòng chỉ nhập số lượng từ 1 đến 100.000");
                return null;
            }
            return val;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chỉ nhập số ở Số lượng");
            return null;
        }
    }

    private BigDecimal parseDonGia(String gia) {
        try {
            long v = Long.parseLong(gia);
            if (v <= 0 || v > 2_000_000_000L) {
                JOptionPane.showMessageDialog(this, "Vui lòng chỉ nhập giá từ 1 đến 2.000.000.000");
                return null;
            }
            return BigDecimal.valueOf(v);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chỉ nhập số ở Giá");
            return null;
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textField.setBackground(new Color(255, 255, 255, 200));
        return textField;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(46, 204, 113));
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(39, 174, 96));
            }
            @Override public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(46, 204, 113));
            }
        });
    }
}


