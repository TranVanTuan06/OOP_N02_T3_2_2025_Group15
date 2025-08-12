package cafemanager.view.panel;

import cafemanager.model.ChiTietDon;
import cafemanager.model.DonHang;
import cafemanager.model.Mon;
import cafemanager.view.MainUI;
import cafemanager.xml.DonHangXML;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NhapDon extends JPanel {

    private JTextField soLuongField;
    private JTextField giaField;
    private JTextArea ghiChuArea;

    public NhapDon() {
        initComponents();
//        setTitle("Nhập Đơn Hàng");
        setSize(500, 400);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(52, 152, 219);
                Color color2 = new Color(41, 128, 185);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("NHẬP ĐƠN HÀNG");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(5, 2, 15, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form components
        JLabel tenMonLabel = createLabel("Tên Món:");

        // ✅ ComboBox món ăn
        JComboBox<String> tenMonComboBox = new JComboBox<>(new String[]{
            "Cà phê sữa", "Cà phê đen", "Trà đào", "Trà sữa", "Sinh tố", "Nước cam"
        });
        tenMonComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel soLuongLabel = createLabel("Số Lượng:");
        soLuongField = createTextField();

        JLabel giaLabel = createLabel("Giá:");
        giaField = createTextField();

        JLabel khachHangLabel = createLabel("Khách Hàng:");
        JTextField khachHangField = createTextField();

        JLabel ghiChuLabel = createLabel("Ghi Chú:");
        ghiChuArea = new JTextArea(3, 20);
        ghiChuArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        ghiChuArea.setLineWrap(true);
        ghiChuArea.setWrapStyleWord(true);
        JScrollPane ghiChuScroll = new JScrollPane(ghiChuArea);
        ghiChuScroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        // Add components to form panel
        formPanel.add(tenMonLabel);
        formPanel.add(tenMonComboBox); // ✅ dùng ComboBox thay cho TextField
        formPanel.add(soLuongLabel);
        formPanel.add(soLuongField);
        formPanel.add(giaLabel);
        formPanel.add(giaField);
        formPanel.add(khachHangLabel);
        formPanel.add(khachHangField);
        formPanel.add(ghiChuLabel);
        formPanel.add(ghiChuScroll);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton nhapDonButton = new JButton("Nhập Đơn");
        styleButton(nhapDonButton);
        nhapDonButton.addActionListener((ActionEvent e) -> {
            String monDaChon = (String) tenMonComboBox.getSelectedItem();
            String sl = soLuongField.getText().trim();
            String gia1 = giaField.getText().trim();
            String tenKhach = khachHangField.getText().trim();
            String ghiChu = ghiChuArea.getText().trim();
            if (sl.isEmpty() || gia1.isEmpty() || tenKhach.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
            } else {
                int solg = getSL(sl);
                double gia = getGia(gia1);

                DonHang donHang = finDH(tenKhach);
                if (donHang != null) {
                    ChiTietDon ctDH = donHang.getMon(monDaChon);
                    if (ctDH == null) {
                        donHang.getChiTiet().add(new ChiTietDon(new Mon(monDaChon, gia), solg));
                        donHang.setGhiChu(ghiChu);
                    } else {
                        ctDH.setSoLuong(ctDH.getSoLuong() + solg);
                    }

                    MainUI.ghiVaoXML();
                    soLuongField.setText("");
                    giaField.setText("");
                    ghiChuArea.setText("");
                } else {
                    donHang = new DonHang(tenKhach);
                    donHang.getChiTiet().add(new ChiTietDon(new Mon(monDaChon, gia), solg));
                    donHang.setGhiChu(ghiChu);
                    MainUI.addDonHang(donHang);
                }
                JOptionPane.showMessageDialog(
                        NhapDon.this,
                        "Bạn đã chọn món: " + monDaChon + "\nĐơn hàng đã được nhập thành công!",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        });

        buttonPanel.add(nhapDonButton);

        // Add all panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame or panel
        add(mainPanel);
    }

    private DonHang finDH(String tenKH) {
        for (DonHang dh : MainUI.listDonHang) {
            if (tenKH.trim().equals(dh.getTenKhach())) {
                return dh;
            }
        }
        return null;
    }

    private boolean checkBan(String tenKH) {
        for (DonHang dh : MainUI.listDonHang) {
            if (tenKH.equals(dh.getTenKhach())) {
                JOptionPane.showMessageDialog(null, "Bàn này đã có người đặt vui lòng chọn bàn khác");
                return true;
            }
        }
        return false;
    }

    private int getSL(String sl) {
        try {
            long soLuong = Integer.parseInt(sl);
            if (soLuong < 100_000 && soLuong > 0) {
                return (int) soLuong;
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập số lượng từ 0 đến 100.000");
                return -1;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập số ở Số lượng");
            return -1;
        }

    }

    private double getGia(String gia) {
        try {
            long soLuong = Integer.parseInt(gia);
            if (soLuong < Integer.MAX_VALUE && soLuong > 0) {
                return (int) soLuong;
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập giá từ 0 đến 2 tỷ");
                return -1;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Vui lòng chỉ nhập số ở Giá");
            return -1;
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

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(39, 174, 96));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(46, 204, 113));
            }
        });
    }

    public static void main(String[] args) {
        try {
            // Set modern look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Create and show the form
            SwingUtilities.invokeLater(() -> {
                new NhapDon().setVisible(true);
            });
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
    }
}