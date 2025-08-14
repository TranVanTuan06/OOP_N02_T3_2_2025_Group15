package com.nocturne.cafemanagerweb.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DonHang {

    private String maDon;
    private String tenKhach;
    private final List<ChiTietDon> chiTiet = new ArrayList<>();
    private String ghiChu;

    public DonHang() {
        this.maDon = taoMaDonNgauNhien(10);
    }

    public DonHang(String tenKhach) { // tạo mới
        this.tenKhach = tenKhach;
        this.maDon = taoMaDonNgauNhien(10);
    }

    public DonHang(String tenKhach, String maDon) { // khởi tạo với mã có sẵn
        this.tenKhach = tenKhach;
        this.maDon = (maDon == null || maDon.isBlank()) ? taoMaDonNgauNhien(10) : maDon;
    }

    // ====== Business methods ======
    public void themChiTiet(ChiTietDon ct) {
        if (ct != null) {
            chiTiet.add(ct);
        }
    }

    public boolean xoaChiTiet(ChiTietDon ct) {
        return chiTiet.remove(ct);
    }

    public ChiTietDon getMon(String tenMon) {
        if (tenMon == null) return null;
        final String key = tenMon.trim();
        return chiTiet.stream()
                .filter(ct -> ct.getMon() != null
                        && ct.getMon().getTenMon() != null
                        && ct.getMon().getTenMon().trim().equals(key))
                .findFirst()
                .orElse(null);
    }

    public BigDecimal tinhTongTien() {
        return chiTiet.stream()
                .map(ChiTietDon::tinhTien)                 // BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add); // cộng an toàn
    }

    public void taoMaNgauNhienMoi() {
        this.maDon = taoMaDonNgauNhien(10);
    }

    // ====== Helpers ======
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

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    // ====== toString/equals/hashCode ======
    @Override
    public String toString() {
        return "Đơn #" + maDon + " - " + (tenKhach == null ? "N/A" : tenKhach)
                + " (" + tinhTongTien() + " đ)";
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

