public class trycostomer {
    public static void main(String[] args) {
        try {
            // Tên lớp cần nạp (nếu có package thì ghi đầy đủ: ví dụ "src.Customer")
            String className = "Customer";
            Class<?> cl = Class.forName(className);

            // Tạo đối tượng bằng constructor có 3 tham số
            Object obj = cl.getConstructor(String.class, String.class, String.class)
                          .newInstance("C002", "Trần Thị B", "0912345678");

            System.out.println("Tạo thành công: " + obj);

        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy lớp Customer!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
