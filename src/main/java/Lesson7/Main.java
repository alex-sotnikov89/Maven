package Lesson7;

public class Main {
    public static void main(String[] args) {
        TestClass testRun = new TestClass();
        Class<?> test1 = Test1.class;
        Test1 test11 = new Test1();
        testRun.start(test1);
        testRun.start(test11);
    }
}
