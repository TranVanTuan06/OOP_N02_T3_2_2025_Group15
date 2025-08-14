package com.nocturne.cafemanagerweb.model;

import java.math.BigDecimal;

public class Mon {
    private Long id;
    private String ten;
    private BigDecimal gia;
    public Mon() {}
    public Mon(Long id, String ten, BigDecimal gia) { this.id = id; this.ten = ten; this.gia = gia; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    public BigDecimal getGia() { return gia; }
    public void setGia(BigDecimal gia) { this.gia = gia; }
}
