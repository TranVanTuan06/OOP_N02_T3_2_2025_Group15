import java.util.*;
public class ganbanchokhachService {
    private Map<String, String> danhSachGan = new HashMap<>();
    private Set<String> banDaCokhach = new HashSet<>();
    public boolean ganbanchokhach(String makhachhang, String maban) {
        if(banDaCokhach.contains(maban)) {
            System.out.println("Ban da co khach");
            return false;
        }
        danhSachGan.put(makhachhang, maban);
        banDaCokhach.add(maban);
        System.out.println("gan khach vao ban thanh cong");
        return true;
    }
    public void hienthidanhsachgan() {
        System.out.println("Danh sach gan: ");
        for(Map.Entry<String, String> entry : danhSachGan.entrySet()) {
            System.out.println("khach" + entry.getKey() + " ban " + entry.getValue());
        }
    }
    public static void main(String[] args) {
        ganbanchokhachService service = new ganbanchokhachService();
        service.ganbanchokhach("KH01", "B01");
        service.ganbanchokhach("KH02", "B01");
        service.hienthidanhsachgan();
    }
}
