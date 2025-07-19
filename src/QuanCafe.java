public class QuanCafe {
    private String tenQuan;
    private String diaChi;

    public QuanCafe(String tenQuan, String diaChi) {
        this.tenQuan = tenQuan;
        this.diaChi = diaChi;
    }

    public String getTenQuan() {
        return tenQuan;
    }

    public void setTenQuan(String tenQuan) {
        this.tenQuan = tenQuan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean kiemTraDuLieu() {
        return tenQuan != null && !tenQuan.isEmpty()
            && diaChi != null && !diaChi.isEmpty();
    }

    public void luuQuanCafe() {
        if (kiemTraDuLieu()) {
            System.out.println("Quán cafe đã được lưu thành công!");
        } else {
            System.out.println("Thông tin không hợp lệ. Không thể lưu.");
        }
    }

    public void hienThiQuan() {
        System.out.println("Quán: " + tenQuan + " | Địa chỉ: " + diaChi);
    }

    public void xoaQuan() {
        tenQuan = null;
        diaChi = null;
        System.out.println("Đã xóa thông tin quán cafe.");
    }

    public void capNhatThongTin(String tenMoi, String diaChiMoi) {
        this.tenQuan = tenMoi;
        this.diaChi = diaChiMoi;
        System.out.println("Đã cập nhật thông tin quán.");
    }
}

