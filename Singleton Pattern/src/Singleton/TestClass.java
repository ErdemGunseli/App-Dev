package Singleton;

// We have the constructor of the class as private and a public method to instantiate the class a maximum of 1 time.
public class TestClass {
    private String name;

    private static TestClass instance;

    // The synchronised keyword makes the method thread-safe, meaning only a single thread can call it at a time.
    public static synchronized TestClass getInstance(String name) {
        if (null == instance){ // If the instance is null, we know that it is the first time we are calling the method.
            instance = new TestClass(name);
        }
        return instance;
    }

    private TestClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // The toString method can be used to get the address of the class.
    // If we do not want this to be seen, we can override it

    @Override
    public String toString(){
        // return super.toString();
        return ("Test Class \nName: " + this.name);

    }
}
