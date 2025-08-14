package com.nocturne.cafemanagerweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ChiTietDon {

    @Column(name = "ten_mon", length = 120, nullable = false)
    private String tenMon;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    // Đơn giá VND; dùng BigDecimal để chính xác số học tiền tệ
    @Column(name = "don_gia", precision = 19, scale = 2, nullable = false)
    private BigDecimal donGia;

    @Column(name = "ghi_chu", length = 300)
    private String ghiChu;

    public ChiTietDon() {
    }

    public ChiTietDon(String tenMon, Integer soLuong, BigDecimal donGia, String ghiChu) {
        this.tenMon = tenMon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.ghiChu = ghiChu;
    }

    /** Thành tiền = đơn giá * số lượng */
    public BigDecimal tinhTien() {
        if (soLuong == null || donGia == null) return BigDecimal.ZERO;
        return donGia.multiply(BigDecimal.valueOf(soLuong.longValue()));
    }

    // Getters/Setters
    public String getTenMon() { return tenMon; }
    public void setTenMon(String tenMon) { this.tenMon = tenMon; }
    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }
    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    @Override
    public String toString() {
        return tenMon + " x" + soLuong + " @" + donGia + " = " + tinhTien();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietDon)) return false;
        ChiTietDon that = (ChiTietDon) o;
        return Objects.equals(tenMon, that.tenMon)
            && Objects.equals(soLuong, that.soLuong)
            && Objects.equals(donGia, that.donGia)
            && Objects.equals(ghiChu, that.ghiChu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenMon, soLuong, donGia, ghiChu);
    }
}

