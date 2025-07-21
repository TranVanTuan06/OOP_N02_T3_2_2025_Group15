package test;
import com.cafe.menu.*;

public class StaffManagerTest {
    public static void main(String[] args) {
        StaffManager sm = new StaffManager();
        sm.addStaff("Nguyễn Văn A", "Phục vụ", 5000000);
        sm.addStaff("Trần Thị B", "Quản lý", 9000000);
        sm.displayStaff();
        sm.updateSalary("Nguyễn Văn A", 5500000);
        sm.updateRole("Trần Thị B", "Giám sát");
        sm.removeStaffByName("Nguyễn Văn A");
        sm.displayStaff();
    }
}

