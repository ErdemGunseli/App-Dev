package StaticTest;

public class Test {

    // Using the static keyword causes the name to be italicised
    // This indicates that the variable belongs to the object itself and not to the instances of the object.
    // Static Variables are not present in constructors but can have getters and setters.

    public static String name;
    public int age;
    public String gender;

//    Static variables are useful if we know that for every instance of a class, a variable will be the same.

    public Test(int age, String gender) {
        this.age = age;
        this.gender = gender;
    }

    // There can also be static methods that belong to the class itself and not to any instance of a class:
    // Non-Static methods and variables cannot be used inside a static method!!!
    public static void printName(){
        System.out.println(Test.name);

    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Test.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
