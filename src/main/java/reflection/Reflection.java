package reflection;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflection {
    interface I {
        void method1();
        void method2();
    }
    class A implements I {

        @Override
        public void method1() {

        }

        @Override
        @Deprecated
        public void method2() {

        }
    }

    public static void main(String[] args) {
        try {
            Method m1 = A.class.getMethod("method1");
            System.out.println(Arrays.toString(m1.getDeclaredAnnotations()));
            Method m2 = A.class.getMethod("method2");
            System.out.println(Arrays.toString(m2.getDeclaredAnnotations()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
