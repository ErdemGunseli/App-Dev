package StaticTest;

public class Main {

    public static void main(String[] args) {

        Test.name = "George";
        Test.printName();

        Test testClass = new Test(20, "Male");


        System.out.println(Test.name + testClass.getName());

//        Because the name variable is static and does not correspond to an instance of the class but the class itself, we can set it before instantiation.

    }
}
