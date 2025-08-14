package com.nocturne.cafemanagerweb.service;

import com.nocturne.cafemanagerweb.model.ChiTietDon;
import com.nocturne.cafemanagerweb.model.DonHang;
import com.nocturne.cafemanagerweb.repo.DonHangRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DonHangService {
    private final DonHangRepository repo;

    public DonHangService(DonHangRepository repo) {
        this.repo = repo;
    }

    public List<DonHang> all() { return repo.findAll(); }
    public DonHang get(String maDon) { return repo.findById(maDon).orElse(null); }
    public DonHang save(DonHang dh) { return repo.save(dh); }
    public void delete(String maDon) { repo.deleteById(maDon); }

    // tiện ích thêm món
    public DonHang addItem(String maDon, String tenMon, Integer soLuong, BigDecimal donGia, String ghiChu) {
        DonHang dh = get(maDon);
        if (dh == null) return null;
        dh.themChiTiet(new ChiTietDon(tenMon, soLuong, donGia, ghiChu));
        return repo.save(dh);
    }
}

