
public class QuanCafeTest {
    public static void main(String[] args) {
        QuanCafe cafeMoc = new QuanCafe("Cafe Mộc", "123 Trần Phú, Hà Nội");

        cafeMoc.hienThiQuan();
        cafeMoc.luuQuanCafe();

        cafeMoc.capNhatThongTin("Cafe Mộc Hà Nội", "456 Nguyễn Trãi, Hà Nội");
        cafeMoc.hienThiQuan();

        cafeMoc.xoaQuan();
        cafeMoc.hienThiQuan();
    }
}

