/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafemanager.view.panel;

/**
 *
 * @author ADMIN
 */
import cafemanager.model.DonHang;
import cafemanager.view.MainUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
    private List<DonHang> filteredList = new ArrayList<>();

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

        // Cột bảng: Mã Đơn, Tên Khách, Tổng Tiền
        String[] columns = {"Mã Đơn", "Khách Hàng", "Tổng Tiền"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa dữ liệu trong bảng
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel phân trang và nút xóa
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel pagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnPrev = new JButton("Trang trước");
        JButton btnNext = new JButton("Trang sau");
        lblPage = new JLabel();
        pagePanel.add(btnPrev);
        pagePanel.add(lblPage);
        pagePanel.add(btnNext);

        // Panel chứa phân trang và tổng tiền
        lblTongTien = new JLabel("Tổng tiền: 0đ");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(lblTongTien);

        bottomPanel.add(pagePanel, BorderLayout.WEST);
        bottomPanel.add(rightPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Sự kiện nút tìm
        btnTim.addActionListener(e -> {
            currentPage = 1;
            applyFilterAndDisplay();
        });

        // Phân trang
        btnPrev.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                updateTable();
            }
        });

        btnNext.addActionListener(e -> {
            if (currentPage < totalPages) {
                currentPage++;
                updateTable();
            }
        });

        // Load dữ liệu ban đầu
        applyFilterAndDisplay();
    }

    private void applyFilterAndDisplay() {
        String search = tfTimKiem.getText().trim().toLowerCase();
        filteredList.clear();

        if (search.isEmpty()) {
            filteredList.addAll(MainUI.listThongKeDonHang);
        } else {
            for (DonHang dh : MainUI.listThongKeDonHang) {
                if (dh.getTenKhach().toLowerCase().contains(search)) {
                    filteredList.add(dh);
                }
            }
        }

        currentPage = 1;
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0);

        int totalRows = filteredList.size();
        totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);

        int start = (currentPage - 1) * rowsPerPage;
        int end = Math.min(start + rowsPerPage, totalRows);
        DecimalFormat formatter = new DecimalFormat("#,###");

        for (int i = start; i < end; i++) {
            double totalAll = 0;
            for (DonHang dh : filteredList) {
                totalAll += dh.tinhTongTien();
            }
            String formattedTotal = formatter.format(totalAll).replace(",", ".") + "đ";
            lblTongTien.setText("Tổng tiền: " + formattedTotal);
            DonHang dh = filteredList.get(i);
            double tongTien = dh.tinhTongTien();
            String formattedMoney = formatter.format(tongTien) + "đ";
            formattedMoney = formattedMoney.replace(",", ".");
            Object[] row = {
                dh.getMaDon(),
                dh.getTenKhach(),
                formattedMoney
            };

            tableModel.addRow(row);
        }

        lblPage.setText("Trang " + currentPage + " / " + totalPages);
    }
}
