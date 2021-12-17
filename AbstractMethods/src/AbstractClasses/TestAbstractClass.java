package AbstractClasses;

// Abstract classes are those that cannot be instantiated.
// They function primarily as a base for a subclass.
// They can have constructors, but they are used to build the object, not initialise it.
// If an abstract class has a constructor, any classes inheriting from it must also have a constructor.

public abstract class TestAbstractClass {

    // We can have abstract methods directly in a class, and like in interfaces, they cannot have bodies.
    public abstract void printName(String name);

    // Inside abstract classes, we can have non-abstract methods:
    public void print(String text){
        System.out.println(text);
    }


}
