public class OuterClassTest {
    int var1 = 42;

    public class InnerClassTest {
        public void displayVar1() {
            System.out.println("var1: " + OuterClassTest.this.var1);
        }
    }

    public static void main(String[] args) {
        OuterClassTest outer = new OuterClassTest();
        OuterClassTest.InnerClassTest inner = outer.new InnerClassTest();
        inner.displayVar1();
    }
}

