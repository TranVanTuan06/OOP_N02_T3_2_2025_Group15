public class trycostomer {
    public static void main(String[] args) {
        try {
            String className = "Customer";
            Class<?> cl = Class.forName(className);
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
