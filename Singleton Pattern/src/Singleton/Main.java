package Singleton;

public class Main {

    public static void main(String[] args) {

        // We use the Singleton Pattern when we want to ensure that there is only 1 instance of a class in the application.


        TestClass testClass = TestClass.getInstance("Hello World");

        System.out.println(testClass.toString());


    }
}
