public class Tryganban {
    public static void main(String[] args) {
        try {
            String className = "Customer";
            Class<?> cl = Class.forName(className);
            Object obj = cl.getDeclaredConstructor().newInstance();
            System.out.println("Đã tạo đối tượng: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}