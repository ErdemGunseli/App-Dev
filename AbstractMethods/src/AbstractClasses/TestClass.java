package AbstractClasses;

// When we want to use abstract classes within a separate class, instead of using the keyword 'implements', we use 'extends'.
// This is because the abstract class is the parent of this class.

// When using abstract classes instead of interfaces, we can only have 1 abstract class that is being used whereas there can be multiple interfaces.
public class TestClass extends TestAbstractClass {


    @Override
    public void printName(String name) {
        System.out.println("Name:" + name);
    }
}
