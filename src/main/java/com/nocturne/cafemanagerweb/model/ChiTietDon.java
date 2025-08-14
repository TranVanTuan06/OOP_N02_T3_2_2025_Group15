package com.nocturne.cafemanagerweb.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ChiTietDon {
    private Mon mon;
    private int soLuong;

    public ChiTietDon() {}

    public ChiTietDon(Mon mon, int soLuong) {
        this.mon = mon;
        this.soLuong = Math.max(0, soLuong);
    }

    public BigDecimal tinhTien() {
        if (mon == null || mon.getGia() == null) return BigDecimal.ZERO;
        return mon.getGia().multiply(BigDecimal.valueOf(soLuong));
    }

    public Mon getMon() { return mon; }
    public void setMon(Mon mon) { this.mon = mon; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = Math.max(0, soLuong); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietDon)) return false;
        ChiTietDon that = (ChiTietDon) o;
        return Objects.equals(mon != null ? mon.getTen() : null,
                              that.mon != null ? that.mon.getTen() : null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mon != null ? mon.getTen() : null);
    }
}
