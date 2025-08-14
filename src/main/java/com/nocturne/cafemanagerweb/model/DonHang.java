package com.nocturne.cafemanagerweb.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "don_hang")
public class DonHang {

    /** Khóa chính là mã đơn dạng chuỗi ngẫu nhiên (ví dụ 10 ký tự A-Z0-9) */
    @Id
    @Column(length = 32)
    private String maDon;

    @Column(length = 150)
    private String tenKhach;

    @ElementCollection
    @CollectionTable(name = "don_hang_chi_tiet", joinColumns = @JoinColumn(name = "ma_don"))
    private List<ChiTietDon> chiTiet = new ArrayList<>();

    @Column(length = 500)
    private String ghiChu;

    public DonHang() {
        this.maDon = taoMaDonNgauNhien(10);
    }

    public DonHang(String tenKhach) {
        this.tenKhach = tenKhach;
        this.maDon = taoMaDonNgauNhien(10);
    }

    public DonHang(String tenKhach, String maDon) {
        this.tenKhach = tenKhach;
        this.maDon = (maDon == null || maDon.isBlank()) ? taoMaDonNgauNhien(10) : maDon;
    }

    // ====== Business ======

    /** Thêm 1 dòng chi tiết (món) vào đơn */
    public void themChiTiet(ChiTietDon ct) {
        if (ct != null) chiTiet.add(ct);
    }

    /** Xóa 1 dòng chi tiết (nếu tồn tại) */
    public boolean xoaChiTiet(ChiTietDon ct) {
        return chiTiet.remove(ct);
    }

    /** Tìm món theo tên (so sánh chính xác sau trim) */
    public ChiTietDon getMon(String tenMon) {
        if (tenMon == null) return null;
        final String key = tenMon.trim();
        return chiTiet.stream()
                .filter(ct -> ct.getTenMon() != null && ct.getTenMon().trim().equals(key))
                .findFirst()
                .orElse(null);
    }

    /** Tổng tiền đơn hàng = sum(thành tiền từng chi tiết) */
    public BigDecimal tinhTongTien() {
        return chiTiet.stream()
                .map(ChiTietDon::tinhTien)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /** Sinh mã mới (nếu cần) */
    public void taoMaNgauNhienMoi() {
        this.maDon = taoMaDonNgauNhien(10);
    }

    /** Helper: sinh mã đơn ngẫu nhiên */
    public static String taoMaDonNgauNhien(int doDai) {
        String kyTu = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder ma = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < doDai; i++) {
            int index = random.nextInt(kyTu.length());
            ma.append(kyTu.charAt(index));
        }
        return ma.toString();
    }

    // ====== Getters/Setters ======
    public String getMaDon() { return maDon; }
    public void setMaDon(String maDon) { this.maDon = maDon; }

    public String getTenKhach() { return tenKhach; }
    public void setTenKhach(String tenKhach) { this.tenKhach = tenKhach; }

    public List<ChiTietDon> getChiTiet() { return chiTiet; }
    public void setChiTiet(List<ChiTietDon> chiTiet) { this.chiTiet = chiTiet; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    // ====== Debug/toString & equals/hashCode ======
    @Override
    public String toString() {
        String items = chiTiet.stream()
                .map(ChiTietDon::toString)
                .collect(Collectors.joining(", "));
        return "Đơn #" + maDon + " - " + (tenKhach == null ? "N/A" : tenKhach)
                + " (" + tinhTongTien() + " đ) [" + items + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DonHang)) return false;
        DonHang donHang = (DonHang) o;
        return Objects.equals(maDon, donHang.maDon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDon);
    }
}



