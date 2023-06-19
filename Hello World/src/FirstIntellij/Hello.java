package FirstIntellij;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class Hello {
    // No user interfaces will be used for the java code as they are used in Android Studio.

    // psvm + tab
    // A class and this main method is required to run the program.
    public static void main(String[] args) {

//        System.out.print("Hello World");
//        sout + tab
//        System.out.println("Hello World");

//        Declaring Variables
//        int myNumber = 1;
//        long myLong = 1;
//        An integer can store up to 2^31; a long can store up to 2^63

//        double myDouble = 1.5;
//        float myFloat = (float) 1.5;
//        Double and float can be used for decimals. Double can be used for larger values.

//        char myChar = 'M';    \ and unicode value can also be used.
//        String myString = "Hello"

//        boolean myBoolean = true;

//        a ++ is equivalent to a += 1 and a = a + 1
//        a -- is equivalent to a -= 1 and a = a - 1
//        a /= 2 is equivalent to a = a / 2
//        a *= 2 is equivalent to a = a * 2

//        || is OR
//        && is AND

//    switch (a) {
//        case 1:
//            System.out.println("A is 1");
//            break;
//        default:
//            System.out.println("A is not 1");
//            break;
//        }


//        break exits out of loops and continue goes to the next cycle.

//        This is the syntax of For loops:
//        for (int i=1; i<=10; i++) {
//            System.out.println("This is number: " + i);
//
//        }

//        This is the syntax of While loops:
//        int counter = 1;
//        while (counter < 11) {
//            System.out.println("This is number: " + counter);
//            counter++;
//        }


//        Scanners are used for inputs in java
//        Scanner myScanner = new Scanner(System.in);

//        This is the syntax of Do While loops:
//        int age = 0;
//        do {
//            System.out.print("Please enter your age: ");
//            age = myScanner.nextInt();
//
//        }while (age < 16 || age > 90);

//        myScanner.next() can be used for inputting strings

//        There are also For Each loops <<<<<<<<<

//        To generate a random number, first import java.util.Random. Then, follow this syntax:
//        adding a number in the argument limits the range between 0 and said number - 1.

//        Random random = new Random();
//        int myRandomNumber = random.nextInt(10);
//        System.out.println(myRandomNumber);


//        In java, array indexes start at 0.
//        Array sizes are static.

//        Creating an Array of Strings:
//        int size = 5;
//        String[] myArray = new String[size];
//
//        myArray[0] = "Hi";

//        System.out.println(myArray[0]);
//
//        Printing an array:
//        String[] namesArray = {"A", "B", "C", "D", "E", "F"};
//
//        for (int i = 0; i < namesArray.length; i++) {
//            System.out.println(namesArray[i]);
//        }
//

//        The following syntax should be used when comparing strings:
//        a.equals(b)


//        Syntax of a class:
//        public class Phone {
//
////    Classes represent objects - in this case, we are representing a phone.
////    Objects can have different properties. A phone may have a screen size, name, etc.
////    For this reason, we have properties for classes.

////    private, public or protected can be used as access modifiers. If none are used, the default is public.
//
//            String name;
//            double screenSize;
//            int memoryRam;
//            private int camera;

//    Constructor:
//    public Phone(String name, double screenSize, int memoryRam) {
//            this.name = name;
//            this.screenSize = screenSize;
//            this.memoryRam = memoryRam;


//        Class syntax with inheritance:

//        public class Bird extends Animal {
//
//        super(name, colour, legs, hasTail);
//          }

//        When overriding an inherited function, simply define a new function with the same name and parameters.
//        To add something to the function instead of overwriting, use the following syntax and add statements as needed:
//        super.myFunction(parameter)


//    Array sizes in Java are immutable, meaning they cannot be changed whilst the program is running.
//        If we needed to add another name to this array, we may need to do the following:

//        String[] names = {"David", "Bob", "John"};
//
//        String[] newNames = new String[4];
//        for (int i =  0; i < names.length; i++){
//           newNames[i] = names[i];
//        }
//        newNames[3] = "Tom";
//
//        for (int i = 0; i < newNames.length; i++){
//            System.out.println(newNames[i]);
//        }

//        As demonstrated above, this method is not ideal, and array lists can instead be used:
//        Array lists are mutable.
//
//        ArrayList<String> names1 = new ArrayList<>();
//
//        List<String> names2 = new ArrayList<>();

//        Primitive data types such as integers, booleans, longs etc. cannot be used when creating array lists.
//        To bypass this, built-in java classes can be used, passing 'Integer' instead of 'String' to use integer array lists.
//
//        names1.add("John");
//        names1.add("Will");
//        names1.add("Alex");
//        names2.add("David");
//        names2.add("James");

//        We can use the following function to sort array lists:

//        names1.sort();

//        add and get functions can be used on array lists to interact with their content.

//        System.out.println(names1.get(0));
//        System.out.println(names2.get(0));

//        System.out.println("The size of the first list is " + names1.size() + "\nAnd the size of the second list is " + names2.size());


//        This procedure will remove specific elements:
//        names1.remove("Will");

//        This will return false, as we have removed Will.
//        names1.contains("Will");

//        This will remove every element from the list.
//        names1.clear();

//        This will return True as we have cleared the list.
//        names1.empty();


//        The syntax for maps (similar to dictionaries):
//        Maps need to be imported before they can be used through: java.util.Map;

//        The first argument is the data type of our keys, and the second is the data type of our values.
//        HashMap is a class

//        Map<String, String> emailList = new HashMap<>();

//        Similarly to collections, we cannot directly use primitive data types but can instead use the equivalent java class.

//        put is used instead of add, and get is used to get the values.

//        emailList.put("John", "john@gmail.com");
//        emailList.put("David", "david@gmail.com");

//        System.out.println(emailList.get("John"));

//        System.out.println(emailList.size());

//        emailList.remove("John");

//        System.out.println(emailList.size());

//        ContainsKey and ContainsValue return boolean values depending on whether the map contains the key or value passed in the parameter.

//        This returns False as we have removed John:
//        System.out.println(emailList.containsKey("John"));

//        This returns True as we still have David and his email:
//        System.out.println(emailList.containsValue("david@gmail.com"));

//        We have size, clear, isEmpty and values methods.

//        We have an interface in java called Collection which is the generic type of all the collections available in Java.




//        Here, we are using the Student class as the data type of our array list:

//        ArrayList<Student> students = new ArrayList<>();
//        students.add(new Student("Erdem", 3077));
//        students.add(new Student("John", 12345));
//        students.add(new Student("George", 815165));


//        The following is the syntax of For Each Loop:
//        We pass the data type (class) of the list to be iterated over:

//        for (Student student: students){
//            System.out.println("\nName: " + student.getName() + "\nID: " + student.getId());
//        }



//        The main method has a public access modifier, it is static, does not return anything, and has a string array called args as a parameter.
//        Since the main method is static, there can only be 1 instance of this method in the entire application.





    }
}