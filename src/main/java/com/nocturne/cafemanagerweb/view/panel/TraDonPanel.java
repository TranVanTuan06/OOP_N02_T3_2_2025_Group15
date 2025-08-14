package com.nocturne.cafemanagerweb.view.panel;

import com.nocturne.cafemanagerweb.model.ChiTietDon;
import com.nocturne.cafemanagerweb.model.DonHang;
import main.java.com.nocturne.cafemanagerweb.view.MainUI;
import main.java.com.nocturne.cafemanagerweb.xml.DonHangXML;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TraDonPanel extends JPanel {

    private int currentPage = 1;
    private int rowsPerPage = 10;
    private int totalPages;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblPage;
    private JTextField tfTimKiem;
    private java.util.List<Object[]> allRows = new ArrayList<>();

    public TraDonPanel() {
        setLayout(new BorderLayout());

        // Tiêu đề
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Trả Đơn", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(label, BorderLayout.NORTH);

        // Thanh tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfTimKiem = new JTextField(20);
        JButton btnTim = new JButton("Tìm");
        JButton btnXoa = new JButton("Xóa");

        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchPanel.add(tfTimKiem);
        searchPanel.add(btnTim);
        searchPanel.add(btnXoa);

        btnTim.addActionListener(e -> {
//            String search = tfTimKiem.getText();
//            DonHang searchDH = timKiem(search);
//            if (searchDH != null) {

            currentPage = 1;
            applyFilterAndDisplay();
//            }

        });

        btnXoa.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin từ dòng đã chọn
            String maDonStr = table.getValueAt(selectedRow, 0).toString();
            String tenMon = table.getValueAt(selectedRow, 2).toString();

            boolean daXoa = false;

            // Tìm đơn hàng phù hợp và xóa món
            for (int i = 0; i < MainUI.listDonHang.size(); i++) {
                DonHang dh = MainUI.listDonHang.get(i);
                if (dh.getMaDon().equals(maDonStr)) {
                    List<ChiTietDon> chiTiet = dh.getChiTiet();
                    for (int j = 0; j < chiTiet.size(); j++) {
                        if (chiTiet.get(j).getMon().getTenMon().equalsIgnoreCase(tenMon)) {
                            chiTiet.remove(j);
                            daXoa = true;
                            break;
                        }
                    }

                    // Nếu đơn hàng không còn món → xóa cả đơn
                    if (dh.getChiTiet().isEmpty()) {
                        MainUI.listDonHang.remove(i);
                    }

                    break;
                }
            }

            if (daXoa) {
                JOptionPane.showMessageDialog(null, "Đã xóa món \"" + tenMon + "\" trong đơn #" + maDonStr, "Thành công", JOptionPane.INFORMATION_MESSAGE);
                DonHangXML.ghi(MainUI.listDonHang);
            } else {
                JOptionPane.showMessageDialog(null, "Không thể xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            // Cập nhật lại bảng
            applyFilterAndDisplay();
        });

        titlePanel.add(searchPanel, BorderLayout.SOUTH);
        add(titlePanel, BorderLayout.NORTH);

        // Cột
        String[] columns = {"Mã Đơn", " Khách Hàng", "Tên Món", "Số Lượng", "Giá", "Tổng"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép sửa dữ liệu trong bảng
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Nút điều khiển phân trang
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel pagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnPrev = new JButton("Trang trước");
        JButton btnNext = new JButton("Trang sau");
        lblPage = new JLabel();
        pagePanel.add(btnPrev);
        pagePanel.add(lblPage);
        pagePanel.add(btnNext);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnThanhToan = new JButton("Thanh Toán");
        buttonPanel.add(btnThanhToan);

        bottomPanel.add(pagePanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Sự kiện nút
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

        btnThanhToan.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn để thanh toán.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy thông tin từ dòng đã chọn
            String tenKH = table.getValueAt(selectedRow, 1).toString().trim();
            List<DonHang> listRemove = new ArrayList<>();
            if (!tenKH.isEmpty()) {
                for (DonHang donHang : MainUI.listDonHang) {
                    if (donHang.getTenKhach().equals(tenKH)) {
                        listRemove.add(donHang);
                    }
                }
            }
            MainUI.listDonHang.removeAll(listRemove);
            MainUI.addThongKeDonHang(listRemove);
            MainUI.ghiVaoXML();
            if (tenKH.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn để thanh toán.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(
                    TraDonPanel.this,
                    "Bạn đã thanh toán Khách hàng: " + tenKH + "\nĐơn hàng đã lưu vào thống kê!",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
            );
            updateTable();
        });
        // Load dữ liệu ban đầu
        updateTable();
    }

    public DonHang timKiem(String text) {
        if (text == null || text.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị để tìm kiếm");
            return null;
        }

        String keyword = removeDiacritics(text.trim().toLowerCase());
        List<DonHang> list = MainUI.listDonHang;

        for (DonHang donHang : list) {
            String maDonStr = String.valueOf(donHang.getMaDon());
            String tenKhach = donHang.getTenKhach();

            // Loại bỏ dấu và so sánh
            String maDonNoAccent = removeDiacritics(maDonStr.toLowerCase());
            String tenKhachNoAccent = removeDiacritics(tenKhach.toLowerCase());

            if (maDonNoAccent.contains(keyword) || tenKhachNoAccent.contains(keyword)) {
                return donHang;
            }
        }
        return null; // không tìm thấy
    }

    public static String removeDiacritics(String input) {
        String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private void updateTable() {
        List<DonHang> list = MainUI.listDonHang;
        allRows.clear();

        for (int i = list.size() - 1; i >= 0; i--) {
            DonHang dh = list.get(i);
            for (ChiTietDon ct : dh.getChiTiet()) {
                Object[] row = new Object[]{
                    dh.getMaDon(),
                    dh.getTenKhach(),
                    ct.getMon().getTenMon(),
                    ct.getSoLuong(),
                    String.format("%,.0f", ct.getMon().getGia()),
                    String.format("%,.0f", ct.tinhTien())
                };
                allRows.add(row);
            }
        }

        applyFilterAndDisplay();
    }

    private void applyFilterAndDisplay() {
        String keyword = removeDiacritics(tfTimKiem.getText().trim().toLowerCase());
        List<DonHang> filteredDonHang = new ArrayList<>();

        for (DonHang dh : MainUI.listDonHang) {
            String ma = removeDiacritics(String.valueOf(dh.getMaDon()).toLowerCase());
            String ten = removeDiacritics(dh.getTenKhach().toLowerCase());

            if (ma.contains(keyword) || ten.contains(keyword)) {
                filteredDonHang.add(dh);
            }
        }

        // Tạo dữ liệu dòng từ danh sách đơn hàng đã lọc
        List<Object[]> allRows = getRowDataFromDonHangList(filteredDonHang);

        // Phân trang
        int totalRows = allRows.size();
        totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
        if (currentPage > totalPages) {
            currentPage = totalPages;
        }
        if (currentPage < 1) {
            currentPage = 1;
        }

        tableModel.setRowCount(0);
        int start = (currentPage - 1) * rowsPerPage;
        int end = Math.min(start + rowsPerPage, totalRows);
        for (int i = start; i < end; i++) {
            tableModel.addRow(allRows.get(i));
        }

        lblPage.setText("Trang " + currentPage + "/" + totalPages);
    }

    public List<Object[]> getRowDataFromDonHangList(List<DonHang> donHangList) {
        List<Object[]> rows = new ArrayList<>();
        for (DonHang dh : donHangList) {
            for (ChiTietDon ct : dh.getChiTiet()) {
                Object[] row = new Object[]{
                    dh.getMaDon(),
                    dh.getTenKhach(),
                    ct.getMon().getTenMon(),
                    ct.getSoLuong(),
                    String.format("%,.0f", ct.getMon().getGia()),
                    String.format("%,.0f", ct.tinhTien())
                };
                rows.add(row);
            }
        }
        return rows;
    }
}

