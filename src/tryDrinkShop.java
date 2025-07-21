public class tryDrinkShop {
    public static void main(String[] args) {
        DrinkShop app = new DrinkShop();

        try {
            Customer c1 = new Customer("C001", "Nguyễn Văn A", "0901234567");
            app.addCustomer(c1);

            app.readCustomers();

            app.editCustomer("C001");

            app.deleteCustomer("C001");

            Table t1 = new Table("T01", 4, true);
            app.addTable(t1);

            app.readTables();

            app.editTable("T01");

            app.deleteTable("T01");
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi thao tác với DrinkShopApp:");
            e.printStackTrace();
        } finally {
            app.closeScanner();
        }
    }
}
