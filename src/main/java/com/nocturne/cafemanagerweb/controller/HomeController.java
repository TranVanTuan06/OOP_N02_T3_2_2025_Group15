package com.nocturne.cafemanagerweb.controller;

import com.nocturne.cafemanagerweb.model.DonHang;
import com.nocturne.cafemanagerweb.service.DonHangService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class HomeController {
    private final DonHangService service;
    public HomeController(DonHangService service) { this.service = service; }

    @GetMapping({"/", "/orders"})
    public String list(Model model) {
        model.addAttribute("orders", service.all());
        return "orders/list";
    }

    @GetMapping("/orders/new")
    public String newForm(Model model) {
        model.addAttribute("order", new DonHang());
        return "orders/form";
    }

    @PostMapping("/orders")
    public String create(@ModelAttribute("order") DonHang dh) {
        // DonHang() đã tự tạo maDon, chỉ cần lưu
        service.save(dh);
        return "redirect:/orders/" + dh.getMaDon();
    }

    @GetMapping("/orders/{maDon}")
    public String detail(@PathVariable String maDon, Model model) {
        var dh = service.get(maDon);
        model.addAttribute("order", dh);
        return "orders/detail";
    }

    // Thêm 1 món vào đơn
    @PostMapping("/orders/{maDon}/items")
    public String addItem(@PathVariable String maDon,
                          @RequestParam String tenMon,
                          @RequestParam Integer soLuong,
                          @RequestParam BigDecimal donGia,
                          @RequestParam(required = false) String ghiChu) {
        service.addItem(maDon, tenMon, soLuong, donGia, ghiChu);
        return "redirect:/orders/" + maDon;
    }

    @PostMapping("/orders/{maDon}/delete")
    public String delete(@PathVariable String maDon) {
        service.delete(maDon);
        return "redirect:/orders";
    }
}

