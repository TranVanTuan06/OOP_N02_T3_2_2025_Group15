package com.nocturne.cafemanagerweb.view.panel;

import com.nocturne.cafemanagerweb.model.DonHang;
import com.nocturne.cafemanagerweb.view.MainUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

public class ThongKePanel extends JPanel {

    private JTextField tfTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblPage;
    private int currentPage = 1;
    private int rowsPerPage = 10;
    private int totalPages = 1;
    private JLabel lblTongTien;
    private final List<DonHang> filteredList = new ArrayList<>();

    public ThongKePanel() {
        setLayout(new BorderLayout());
        // Tiêu đề
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Thống Kê Đơn Hàng", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(label, BorderLayout.NORTH);

        // Thanh tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfTimKiem = new JTextField(20);
        JButton btnTim = new JButton("Tìm");

        searchPanel.add(new JLabel("Tìm kiếm theo khách hàng:"));
        searchPanel.add(tfTimKiem);
        searchPanel.add(btnTim);
        titlePanel.add(searchPanel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);

        // Bảng
        String[] columns = {"Mã Đơn", "Khách Hàng", "Tổng Tiền"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel đáy
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel pagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnPrev = new JButton("Trang trước");
        JButton btnNext = new JButton("Trang sau");
        lblPage = new JLabel();
        pagePanel.add(btnPrev);
        pagePanel.add(lblPage);
        pagePanel.add(btnNext);

        lblTongTien = new JLabel("Tổng tiền: 0đ");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 14));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(lblTongTien);

        bottomPanel.add(pagePanel, BorderLayout.WEST);
        bottomPanel.add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Sự kiện
        btnTim.addActionListener(e -> { currentPage = 1; applyFilterAndDisplay(); });
        btnPrev.addActionListener(e -> { if (currentPage > 1) { currentPage--; updateTable(); } });
        btnNext.addActionListener(e -> { if (currentPage < totalPages) { currentPage++; updateTable(); } });

        // Load đầu
        applyFilterAndDisplay();
    }

    private void applyFilterAndDisplay() {
        String search = tfTimKiem.getText() == null ? "" : tfTimKiem.getText().trim().toLowerCase();
        filteredList.clear();

        List<DonHang> src = MainUI.listThongKeDonHang != null ? MainUI.listThongKeDonHang : List.of();

        if (search.isEmpty()) {
            filteredList.addAll(src);
        } else {
            for (DonHang dh : src) {
                String name = dh.getTenKhach();
                if (name != null && name.toLowerCase().contains(search)) {
                    filteredList.add(dh);
                }
            }
        }

        currentPage = 1;
        updateTable();
    }

    private static String formatVND(BigDecimal amount) {
        if (amount == null) amount = BigDecimal.ZERO;
        // đơn giản: dùng DecimalFormat rồi thay , -> . cho nhóm nghìn
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount).replace(",", ".") + "đ";
    }

    private void updateTable() {
        tableModel.setRowCount(0);

        int totalRows = filteredList.size();
        totalPages = Math.max(1, (int) Math.ceil((double) totalRows / rowsPerPage));

        int start = Math.max(0, (currentPage - 1) * rowsPerPage);
        int end = Math.min(start + rowsPerPage, totalRows);

        // TÍNH TỔNG TẤT CẢ ĐƠN (một lần)
        BigDecimal totalAll = filteredList.stream()
                .map(DonHang::tinhTongTien)                     // BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        lblTongTien.setText("Tổng tiền: " + formatVND(totalAll));

        // ĐỔ DỮ LIỆU TRANG HIỆN TẠI
        for (int i = start; i < end; i++) {
            DonHang dh = filteredList.get(i);
            BigDecimal tongTien = dh.tinhTongTien();           // BigDecimal
            Object[] row = {
                    dh.getMaDon(),
                    dh.getTenKhach(),
                    formatVND(tongTien)
            };
            tableModel.addRow(row);
        }

        lblPage.setText("Trang " + currentPage + " / " + totalPages);
    }
}

