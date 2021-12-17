package InnerTest;

public class Test {

    private int age;


    private class TestInnerClass {
        // This is an inner class that can have its own attributes and methods.
        // It will have access to the attributes and methods of the Test class, regardless of their respective access modifiers.


        // Using inner classes may make the code simpler / easier to understand.
        // Inner classes can be private and/or static whereas parent classes cannot.

        // Used for async tasks which can be defined as inner classes.
        private String name;

        public TestInnerClass(String name) {
            this.name = name;
        }

        // It can have access to attributes & methods of the Test class as shown:
       private void currentAndFutureAge() {
           System.out.println("Age Now: " + age);
           System.out.println("Age in 5 Years: " + futureAge(5));

       }



    }

    public Test(int age) {
        this.age = age;
    }

    public int futureAge(int years){return this.age + years;}
}
