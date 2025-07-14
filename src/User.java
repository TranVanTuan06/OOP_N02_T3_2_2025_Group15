public class User {
        private String ten;
        private int tuoi;
        private String sdt;
        public User(String ten, int tuoi, String sdt) {
            this.ten = ten;
            this.tuoi = tuoi;
            this.sdt = sdt;
        }
        public String getTen() {
            return ten;
        }
        public void setTen(String ten) {
            this.ten = ten;
        }
        public int getTuoi() {
            return tuoi;
        }
        public void setTuoi(int tuoi) {
            this.tuoi = tuoi;
        }
        public String Sdt() {
            return sdt;
        }
        public void Sdt(String sdt) {
            this.sdt = sdt;
        }
        public String toString() {
        return "Thông tin người dùng:\n" +
               "Họ tên: " + ten + "\n" +
               "Tuổi: " + tuoi + "\n" +
               "Sdt: " + sdt;
    }
}
