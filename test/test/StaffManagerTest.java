package test;
import com.cafe.menu.StaffManager;
public class StaffManagerTest {
    public static void main(String[] args) {
        StaffManager staff = new StaffManager();
        staff.addStaff("Nguyễn Văn A");
        staff.addStaff("Lê Thị B");
        staff.displayStaff();
    }
}
