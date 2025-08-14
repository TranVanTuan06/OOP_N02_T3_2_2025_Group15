package com.nocturne.cafemanagerweb.model;

public class ChiTietDon {
    private Mon mon;
    private int soLuong;
    public ChiTietDon() {}
    public ChiTietDon(Mon mon, int soLuong) { this.mon = mon; this.soLuong = soLuong; }
    public Mon getMon() { return mon; }
    public void setMon(Mon mon) { this.mon = mon; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
