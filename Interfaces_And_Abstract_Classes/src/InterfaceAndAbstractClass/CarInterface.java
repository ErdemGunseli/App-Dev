package InterfaceAndAbstractClass;

public interface CarInterface {

    // Interfaces are like a contract between 2 parts of a program.
    // Inside interfaces, we can define abstract methods.

    // Interface abstract methods cannot have a body.
    // The use of abstract methods hides the functionality of methods.
    // Instead, we are defining some sort of contract - we know the signature (void return type, string input...)
    // All interface methods have a public access modifier - there cannot be any private methods.

    void start();

    void move(int speed);
}
