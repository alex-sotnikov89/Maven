package Lesson7;

public class Test1 {
    public Test1() {
    }

//    @BeforeSuite
//    private void beforeTest() {
//        System.out.println("Hello Maks!");
//    }

    @Test(redundant = 2)
    private void testTHIS() {
        System.out.println("THIS");
    }

    @Test(redundant = 1)
    private void testIS() {
        System.out.println("IS");
    }

    @Test(redundant = 4)
    private static void testVERY() {
        System.out.println("VERY");
    }
    @Test(redundant = 3)
    private static void testCODE() {
        System.out.println("CODE");
    }

    @AfterSuite
    private void afterTest() {
        System.out.println("After test!");
    }

    @Test()
    private void TestBAD() {
        System.out.println("BAD?");
    }

    @Test(redundant = 10)
    private void Test() {
        System.out.println("Hello TEST!");
    }
}
