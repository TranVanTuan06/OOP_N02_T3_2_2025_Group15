public class tryuser {
    public static void main(String[] args) {
        try {
            // Tạo đối tượng User bình thường
            User user = new User("Nguyễn Văn troc", 1, "0987654321");
            System.out.println("Thông tin User: " + user);

            // Tạo đối tượng Customer bằng cách thủ công
            Customer c = new Customer("C001", "Trần Thị B", "0912345678");
            System.out.println("Thông tin Customer: " + c);

            // Tạo đối tượng Customer bằng reflection (nâng cao)
            String className = "Customer"; // nếu có package thì thêm đầy đủ
            Class<?> cl = Class.forName(className);
            Object obj = cl.getConstructor(String.class, String.class, String.class)
                          .newInstance("C002", "Lê Văn C", "0901122334");

            System.out.println("Tạo Customer qua reflection: " + obj);

        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy lớp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi tạo đối tượng:");
            e.printStackTrace();
        }
    }
}
