package cafemanager.view;

import cafemanager.model.DonHang;
import cafemanager.view.panel.NhapDon;
import cafemanager.view.panel.ThongKePanel;
import cafemanager.view.panel.TimKiemPanel;
import cafemanager.view.panel.TinhTienPanel;
import cafemanager.view.panel.TraDonPanel;
import cafemanager.xml.DonHangXML;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel contentPanel;

    public static List<DonHang> listDonHang = new ArrayList<>();
    public static List<DonHang> listThongKeDonHang = new ArrayList<>();

    public MainUI() {
        initData();
        setTitle("Quản Lý Quán Cafe");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initData() {
        listDonHang = DonHangXML.doc();
        listThongKeDonHang = DonHangXML.docThongKe();
    }

    public static void addDonHang(DonHang dh) {
        listDonHang.add(0, dh);
        DonHangXML.ghi(listDonHang);
    }

    public static void ghiVaoXML() {
        DonHangXML.ghi(listDonHang);
    }

    public static void ghiThongKeVaoXML() {
        DonHangXML.ghiThongKe(listThongKeDonHang);
    }

    public static void addThongKeDonHang(List<DonHang> dh) {
        listThongKeDonHang.addAll(dh);
        DonHangXML.ghiThongKe(listThongKeDonHang);
    }

    private void initUI() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton btnNhapDon = new JButton("Nhập Đơn");
        JButton btnTraDon = new JButton("Trả Đơn");
        JButton btnTinhTien = new JButton("Tính Tiền");
        JButton btnThongKe = new JButton("Thống Kê");
        JButton btnDangXuat = new JButton("Đăng Xuất");

        menuPanel.add(btnNhapDon);
        menuPanel.add(btnTraDon);
        menuPanel.add(btnTinhTien);
        menuPanel.add(btnThongKe);
        menuPanel.add(btnDangXuat);

        // Panel chứa nội dung chính
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Thêm các panel con
        contentPanel.add(new NhapDon(), "nhapdon");
        contentPanel.add(new TraDonPanel(), "tradon");
        contentPanel.add(new TinhTienPanel(), "tinhtien");
        contentPanel.add(new TimKiemPanel(), "timkiem");

        // Sự kiện nút chuyển panel
        btnNhapDon.addActionListener(e -> reloadPanel("nhapdon"));
        btnTraDon.addActionListener(e -> reloadPanel("tradon"));
        btnTinhTien.addActionListener(e -> reloadPanel("tinhtien"));
        btnThongKe.addActionListener(e -> reloadPanel("thongke"));
        btnDangXuat.addActionListener(this::handleLogout);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(menuPanel, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    private void reloadPanel(String name) {
        contentPanel.removeAll();
        switch (name) {
            case "nhapdon":
                contentPanel.add(new NhapDon());
                break;
            case "tradon":
                contentPanel.add(new TraDonPanel());
                break;
            case "thongke":
                contentPanel.add(new ThongKePanel());
                break;
            // thêm các case khác nếu cần
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showCard(String name) {
        cardLayout.show(contentPanel, name);
    }

    private void handleLogout(ActionEvent e) {
        dispose();
        new LoginUI().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }

}