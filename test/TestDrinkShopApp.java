public class TestDrinkShopApp {
    public static void test() {

        DrinkShopApp app = new DrinkShopApp();

        Customer c1 = new Customer("KH01", "Nguyen Van an", "0901372984");
        Customer c2 = new Customer("KH02", "Tran Thi hồng", "0989348274");

        app.addCustomer(c1);
        app.addCustomer(c2);

        System.out.println("========= Danh sách khách hàng sau khi thêm =========");
        app.readCustomers();

        app.deleteCustomer("KH01");
        System.out.println("========= Danh sách khách hàng sau khi xóa KH01 =========");
        app.readCustomers();

        System.out.println("========= Sửa thông tin KH02 =========");
        app.editCustomer("KH02");
        System.out.println("========= Danh sách khách hàng hiện tại =========");
        app.readCustomers();

        Table t1 = new Table("B001", 4, true);
        Table t2 = new Table("B002", 6, false);

        app.addTable(t1);
        app.addTable(t2);

        System.out.println("========= Danh sách bàn sau khi thêm =========");
        app.readTables();

        app.deleteTable("B002");
        System.out.println("========= Danh sách bàn sau khi xóa B002 =========");
        app.readTables();

        System.out.println("========= Sửa thông tin bàn B001 =========");
        app.editTable("B001");
        System.out.println("========= Danh sách bàn hiện tại =========");
        app.readTables();

        app.closeScanner();
    }
    public static void main(String[] args) {
        TestDrinkShopApp.test();
    }
}
